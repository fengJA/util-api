package com.fj.redis.controller;

import com.fj.redis.entity.PagingResultVO;
import com.fj.redis.entity.TbMachineinfo;
import com.fj.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis")
@Api(value = "RedisController", tags = "redis的测试")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "测试Redis写入数据")
    @PostMapping(value = "/custinfo-detail-list")
    public void addDataToRedis(){
        redisService.addDataToRedis();
    }

    @ApiOperation(value = "测试Redisson")
    @GetMapping(value = "/rest-redisson")
    public PagingResultVO<TbMachineinfo> testRedisson(@ApiParam(value = "配件设备号") @RequestParam(value = "deviceNumber",required = false) String deviceNumber,
                                                      @ApiParam(value = "时间") @RequestParam(value = "time",required = false) String time,
                                                      @ApiParam(value = "页码：首次查询不传") @RequestParam(value = "page",required = false) Integer page,
                                                      @ApiParam(value = "页数") @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                                      @ApiParam(value = "页数,除了第一页请求，都传") @RequestParam(value = "total",required = false) Integer total){

        return redisService.testRedis(deviceNumber, time, page, pageSize, total);
    }
}
