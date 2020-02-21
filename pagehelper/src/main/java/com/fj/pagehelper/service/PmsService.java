package com.fj.pagehelper.service;

import com.fj.pagehelper.entity.Pms;
import com.fj.pagehelper.vo.QueryParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PmsService {

    /**
     * 分页根据条件查询数据
     * @param queryParam
     * @return
     */
    List<Pms> queryPageDemoInfo(QueryParam queryParam);
}
