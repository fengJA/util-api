package com.fj.test.controller;

import com.fj.test.gmall.entity.Pms;
import com.fj.test.gmall.service.PmsService;
import com.fj.test.gmall.vo.Param;
import com.fj.test.gmall.vo.PmsQueryParam;
import com.fj.test.gmall.vo.PmsQueyPageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ExtendsController {

    @Autowired
    PmsService pmsService;

    /**
     * 测试继承：封装的实体类Param里面没有设计到Pms类的字段，但是继承了Pms，则也可以在前端收到该数据
     * @param param
     */
    @PostMapping("/test/extends")
    public void testExtends(@RequestBody Param param){
        if (StringUtils.isEmpty(param)){
            System.out.println("没有传入数据");
        }else {
            pmsService.savePms(param);
            System.out.println("数据保存好了");
        }
    }

    /**
     * 测试分页显示数据
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("test/pageInfo")
    public PmsQueyPageInfo pageInfo(@RequestParam("pageSize") Long pageSize, @RequestParam("pageNum") Long pageNum){
        if (StringUtils.isEmpty(pageNum)){
            pageNum = 1L;
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10L;
        }
        PmsQueyPageInfo pmsPage =  pmsService.selectPageInfo(pageNum,pageSize);
        return pmsPage;
    }

    @GetMapping("/test/keyWorldsQuery")
    public PmsQueyPageInfo keyWorldsQuery(PmsQueryParam pmsQueryParam,@RequestParam("pageSize") Long pageSize, @RequestParam("pageNum") Long pageNum){
        if (StringUtils.isEmpty(pageNum)){
            pageNum = 1L;
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10L;
        }
        PmsQueyPageInfo pmsQueyPageInfo = pmsService.keyWorldQuery(pmsQueryParam,pageNum,pageSize);
        return pmsQueyPageInfo;
    }
}
