package com.fj.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fj.redis.entity.CanDataDto;
import com.fj.redis.entity.PagingResultVO;
import com.fj.redis.entity.TbMachineinfo;
import com.fj.redis.mapper.redismapper.RedisMapper;
import com.fj.redis.service.RedisService;
import com.fj.redis.util.config.RedissonConfig;
import com.fj.redis.util.date.DateUtil;
import com.fj.redis.util.valid.ValidateParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImp implements RedisService {

    @Autowired
    private RedissonConfig redissonConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisMapper redisMapper;

    @Override
    public void addDataToRedis() {
        String deviceNumber = "15887986589";
        String nowTime = String.valueOf(DateUtil.changTime("2020-11-17 16:59:63", "yyyy-MM-dd HH:mm:ss"));
        log.info("key:" + deviceNumber + nowTime);
        // 查询数据
        List<CanDataDto> machine = redisMapper.queryMachineInfo();
        Map<String, Object> map = new HashMap<>();
        if (machine.size() > 5){
            for (int i = 0; i < machine.size() / 5; i++) {
                List<CanDataDto> canDataDtos = machine.subList(i * 5, (i + 1) * 5);
                map.put(deviceNumber + nowTime + (i + 1), canDataDtos);
            }
        }
//        redisTemplate.opsForValue().set(deviceNumber + nowTime,"FJJ");
        redisTemplate.opsForHash().putAll(deviceNumber + nowTime, map);
        String s = redisTemplate.opsForValue().get(deviceNumber + nowTime);
        System.out.println(s);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PagingResultVO<TbMachineinfo> testRedis(String deviceNumber, String time, Integer page, Integer pageSize, Integer total) {
        log.info("key:" + deviceNumber + time);
        List<TbMachineinfo> tbMachineinfos = new ArrayList<>();
        List<TbMachineinfo> machine = new ArrayList<>();
        Integer numbers = 0;
        if (ValidateParamUtil.isObjectNull(page)){
            String nowTime = String.valueOf(DateUtil.changTime(time, "yyyy-MM-dd HH:mm:ss"));
            // 查询数据
            machine = redisMapper.queryMachineAll();
            Map<String, Object> map = new HashMap<>();
            if (machine.size() > 0){
                for (int i = 0; i <= machine.size() / pageSize; i++) {
                    if (i == machine.size() / pageSize){
                        List<TbMachineinfo> canDataDtos = machine.subList(i * pageSize, machine.size());
                        map.put(deviceNumber + nowTime + (i + 1), JSONObject.toJSONString(canDataDtos));
                    }else {
                        List<TbMachineinfo> canDataDtos = machine.subList(i * pageSize, (i + 1) * pageSize);
                        map.put(deviceNumber + nowTime + (i + 1), JSONObject.toJSONString(canDataDtos));
                    }
                }
            }
            redisTemplate.opsForHash().putAll("DE", map);
            log.info("DE的过期时间是：" + redisTemplate.getExpire("DE"));
            redisTemplate.opsForHash().putAll(deviceNumber + nowTime, map);
            Map entries = redisTemplate.opsForHash().entries(deviceNumber + nowTime);
            redisTemplate.expire(deviceNumber + nowTime,1 , TimeUnit.HOURS);
            log.info(deviceNumber + nowTime + "过期时间是：" + redisTemplate.getExpire(deviceNumber + nowTime));
            log.info("结果是：" + entries);
            if (!ValidateParamUtil.isObjectNull(entries)){
                tbMachineinfos = JSONObject.parseArray(JSON.toJSON(entries.get(deviceNumber + nowTime + 1)).toString(), TbMachineinfo.class);
            }
            page = 1;
            numbers = machine.size();
        }else {
            Map entries = redisTemplate.opsForHash().entries(deviceNumber + time);
            if (!ValidateParamUtil.isObjectNull(entries)){
                tbMachineinfos = JSONObject.parseArray(JSON.toJSON(entries.get(deviceNumber + time + page)).toString(), TbMachineinfo.class);
            }
            numbers = total;
        }
        return new PagingResultVO<>(page, pageSize, tbMachineinfos, numbers);
    }
}
