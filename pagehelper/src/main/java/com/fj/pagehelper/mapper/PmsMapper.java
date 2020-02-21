package com.fj.pagehelper.mapper;

import com.fj.pagehelper.entity.Pms;
import com.fj.pagehelper.vo.QueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmsMapper {

    List<Pms> queryPageInfo(QueryParam queryParam);
}
