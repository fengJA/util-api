package com.fj.pagehelper.service;

import com.fj.pagehelper.entity.Topic;
import com.fj.pagehelper.entity.TopicInfoResultParam;

import java.util.List;

public interface TopicService {

    void addTopic(Topic topics);

    // 话题详情测试
    List<TopicInfoResultParam> queryTopicInfoDetails(String id);
}
