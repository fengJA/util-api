package com.fj.pagehelper.mapper;

import com.fj.pagehelper.entity.Topic;
import com.fj.pagehelper.entity.TopicInfoResultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper {

    void addParam(Topic topics);

    List<TopicInfoResultParam> queryTopicInfoDetails(String id);
}
