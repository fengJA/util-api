package com.fj.pagehelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    private Integer id;

    private String topicName;
    private Integer topicNum;
    private Integer topicStatus;

    private String topicDate;


}
