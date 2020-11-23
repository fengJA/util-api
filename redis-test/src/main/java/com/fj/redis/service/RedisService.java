package com.fj.redis.service;

import com.fj.redis.entity.PagingResultVO;
import com.fj.redis.entity.TbMachineinfo;

public interface RedisService {

    /**
     * Redis测试写入数据
     */
    void addDataToRedis();

    /**
     * 测试Redisson
     * @param deviceNumber
     * @param time
     * @param page
     * @param pageSize
     * @param total
     * @return
     */
    PagingResultVO<TbMachineinfo> testRedis(String deviceNumber, String time, Integer page, Integer pageSize, Integer total);

}
