package com.fj.pagehelper.controller;

import com.fj.pagehelper.entity.Topic;
import com.fj.pagehelper.entity.TopicInfoResultParam;
import com.fj.pagehelper.service.TopicService;
import com.fj.pagehelper.util.consts.Const;
import com.fj.pagehelper.util.result.ResultUtils;
import com.fj.pagehelper.util.result.ResultVo;
import com.fj.pagehelper.vo.TopicParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "TopicController" ,description = "Topic相关接口")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ApiOperation(value = "添加数据到Topic表")
    @PostMapping("/addData")
    public ResultVo pageInfo(@RequestBody TopicParam topicParam){
        Topic topics = new Topic();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        topics.setTopicName(topicParam.getTopic_name());
        topics.setTopicNum(0);
        topics.setTopicStatus(topicParam.getTopic_status());
        topics.setTopicDate(simpleDateFormat.format(date));

        topicService.addTopic(topics);

        return ResultUtils.success(Const.OPERATION_MSG_SUCESS);
    }

    @ApiOperation(value = "话题详情a")
    @PatchMapping(value = "/topicInfo/querryTopicInfoLists")
    public ResultVo querryTopicInfoListByIds(@RequestParam("id") String id){
        List<TopicInfoResultParam> list = topicService.queryTopicInfoDetails(id);
        return ResultUtils.success(list, Const.OPERATION_MSG_SUCESS);
    }
}
