package com.fj.pagehelper.controller;

import com.fj.pagehelper.entity.Pms;
import com.fj.pagehelper.service.PmsService;
import com.fj.pagehelper.util.consts.Const;
import com.fj.pagehelper.util.result.ResultUtils;
import com.fj.pagehelper.util.result.ResultVo;
import com.fj.pagehelper.vo.QueryParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Api(tags = "PmsController" ,description = "Pms相关接口")
@Api(value = "PmsController", tags = "Pms相关接口")
@RestController
public class PmsController {

    @Autowired
    PmsService pmsService;



    @ApiOperation(value = "分页查询Pms表的数据")
    @GetMapping("/test/pageInfo")
    public ResultVo pageInfo(QueryParam queryParam){
        Page page = PageHelper.startPage(queryParam.getPageNumber(), queryParam.getPageSize(),true);
        List<Pms> pmsPage = pmsService.queryPageDemoInfo(queryParam);

        return ResultUtils.pagerSuccess(pmsPage, Const.QUERY_MSG_SUCESS,page);
    }
}
