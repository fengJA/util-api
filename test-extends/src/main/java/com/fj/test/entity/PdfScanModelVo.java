package com.fj.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfScanModelVo {
    // 项目
    private String project;

    // 单位
    private String unit;

    // 设计值
    private String designValue;

    private List list;
}
