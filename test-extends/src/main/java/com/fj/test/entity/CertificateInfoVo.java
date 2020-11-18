package com.fj.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(value = "CertificateInfoVo", description = "农机证书详情")
public class CertificateInfoVo {
    @ApiModelProperty(value = "主键Id")
    private String id;

    @ApiModelProperty(value = "vin号")
    private String vinNumber;

    @ApiModelProperty(value = "证书编号")
    private String certificateNumber;

    @ApiModelProperty(value = "证书状态")
    private String certificateStatus;

    @ApiModelProperty(value = "制造商名称")
    private String certificateMakeName;

    @ApiModelProperty(value = "制造商地址")
    private String certificateMakeAddress;

    @ApiModelProperty(value = "生产厂商名称")
    private String productName;

    @ApiModelProperty(value = "生产厂商地址")
    private String productAddress;

    @ApiModelProperty(value = "产品型号")
    private String produceModel;

    @ApiModelProperty(value = "产品地址")
    private String produceAddress;

    @ApiModelProperty(value = "涵盖型号")
    private String containModel;

    @ApiModelProperty(value = "公告文件号")
    private String docNum;

    @ApiModelProperty(value = "发证日期")
    private LocalDate beginDate;

    @ApiModelProperty(value = "证书截止")
    private LocalDate endDate;

    @ApiModelProperty(value = "鉴定报告编号")
    private String reportNumber;

    @ApiModelProperty(value = "鉴定机构")
    private String surveyCust;

    @ApiModelProperty(value = "鉴定大纲")
    private String surveyOutline;

    @ApiModelProperty(value = "原证书号")
    private String beforeCertificateNumber;

    @ApiModelProperty(value = "产品大类")
    private String produceType;

    @ApiModelProperty(value = "所属品目")
    private String category;

    @ApiModelProperty(value = "产品名称")
    private String produceName;

    @ApiModelProperty(value = "检测结果")
    private String checkResult;

    @ApiModelProperty(value = "鉴定级别")
    private String checkLevel;

    @ApiModelProperty(value = "型号规格")
    private String standardsType;

    @ApiModelProperty(value = "结构形式")
    private String structure;

    @ApiModelProperty(value = "配套发动机型号规格")
    private String engineStandard;

    @ApiModelProperty(value = "配套发动机额定功率")
    private String enginePower;

    @ApiModelProperty(value = "配套发动机额定转速")
    private String engineSpeed;

    @ApiModelProperty(value = "外形尺寸")
    private String overallDimension;

    @ApiModelProperty(value = "结构质量")
    private String structuralQuality;

    @ApiModelProperty(value = "工作行数")
    private String workNum;

    @ApiModelProperty(value = "使用行距范围")
    private String useSpace;

    @ApiModelProperty(value = "工作幅宽")
    private String  workingWidth;

    @ApiModelProperty(value = "最大卸果穗高度")
    private String maximumHeight;
}
