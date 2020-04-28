package com.fj.pagehelper.entity;


import java.util.List;

public class TopicInfoResultParam {

    // 话题名称
    private String topicName;
    // 应用（渠道类型）
    private String channelType;
    // 帖子数量
    private Integer postNum;
    // 日志详情
    List<OperateHistoryDto> operateHistoryDtos;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public List<OperateHistoryDto> getOperateHistoryDtos() {
        return operateHistoryDtos;
    }

    public void setOperateHistoryDtos(List<OperateHistoryDto> operateHistoryDtos) {
        this.operateHistoryDtos = operateHistoryDtos;
    }
}
