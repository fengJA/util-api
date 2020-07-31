package com.fj.test.entity;

import lombok.Data;

import java.util.List;

@Data
public class PdfScanVo {
    // 证书编号
    private String certificateNo;

    // 证书状态
    private String certificateStatus;

    // 制造商名称
    private String makeName;

    // 制造商地址
    private String makeAddress;

    // 产品型号
    private String productType;

    // 技术规格信息
    List<PdfScanModelVo> pdfScanModelVos;
}
