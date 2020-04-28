package com.fj.pagehelper.service.impl;

import com.fj.pagehelper.entity.Topic;
import com.fj.pagehelper.entity.TopicInfoResultParam;
import com.fj.pagehelper.mapper.TopicMapper;
import com.fj.pagehelper.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImp implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public void addTopic(Topic topics) {
        topicMapper.addParam(topics);
    }

    @Override
    public List<TopicInfoResultParam> queryTopicInfoDetails(String id) {
        List<TopicInfoResultParam> list = topicMapper.queryTopicInfoDetails(id);
        return list;
    }
}
