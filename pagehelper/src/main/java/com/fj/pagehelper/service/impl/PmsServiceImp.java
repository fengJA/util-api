package com.fj.pagehelper.service.impl;

import com.fj.pagehelper.entity.Pms;
import com.fj.pagehelper.mapper.PmsMapper;
import com.fj.pagehelper.service.PmsService;
import com.fj.pagehelper.vo.QueryParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsServiceImp implements PmsService {

    @Autowired
    PmsMapper pmsMapper;


    @Override
    public List<Pms> queryPageDemoInfo(QueryParam queryParam) {
        List<Pms> pmsList = pmsMapper.queryPageInfo(queryParam);
        return pmsList;
    }
}
