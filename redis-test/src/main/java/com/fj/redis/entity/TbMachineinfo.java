package com.fj.redis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 信息管理_农机信息管理
 * </p>
 *
 * @author fengjia
 * @since 2020-05-18
 */
@ApiModel(value = "TbMachineinfo", description = "农机信息")
@TableName(value = "tb_machineinfo")
@Data
public class TbMachineinfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("id")
    @ApiModelProperty(value = "主键Id(MI)")
    private String id;

    @TableField("custId")
    @ApiModelProperty(value = "机构Id")
    private String custId;

    @TableField("macUserId")
    @ApiModelProperty(value = "机手Id")
    private String macUserId;

    @TableField("toolsId")
    @ApiModelProperty(value = "机具Id")
    private String toolsId;

    @ApiModelProperty(value = "一级品目Id")
    @TableField("firstClassId")
    private String firstClassId;

    @ApiModelProperty(value = "一级品目名称")
    @TableField("firstClassName")
    private String firstClassName;

    @ApiModelProperty(value = "二级品目Id")
    @TableField("secondClassId")
    private String secondClassId;

    @ApiModelProperty(value = "二级品目Id")
    @TableField("secondClassName")
    private String secondClassName;

    @ApiModelProperty(value = "品类Id")
    @TableField("machineClassId")
    private String machineClassId;

    @ApiModelProperty(value = "品类名称")
    @TableField("machineClassName")
    private String machineClassName;

    @ApiModelProperty(value = "VIN号")
    @TableField("vinNumber")
    private String vinNumber;

    @ApiModelProperty(value = "农机名称")
    @TableField("machineName")
    private String machineName;

    @ApiModelProperty(value = "品牌")
    @TableField("Brand")
    private String brand;

    @TableField("plateNumber")
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    @TableField("manufacturer")
    @ApiModelProperty(value = "农机制造商")
    private String manufacturer;

    @ApiModelProperty(value = "动力类型名称")
    @TableField("powerType")
    private String powerType;

    @ApiModelProperty(value = "驱动方式名称")
    @TableField("drivingMode")
    private String drivingMode;

    @ApiModelProperty(value = "是否绑定设备：0.否；1.是")
    @TableField("isBindDevice")
    private Boolean isBindDevice;

    @ApiModelProperty(value = "机型型号")
    @TableField("produceModel")
    private String produceModel;

    @ApiModelProperty(value = "指导价")
    @TableField("guidancePrice")
    private BigDecimal guidancePrice;

    @ApiModelProperty(value = "进气方式")
    @TableField("airIntakeMode")
    private String airIntakeMode;

    @ApiModelProperty(value = "排放标准")
    @TableField("emissionStandard")
    private String emissionStandard;

    @ApiModelProperty(value = "型号")
    @TableField("model")
    private String model;

    @TableField("machineTypeName")
    @ApiModelProperty(value = "农机类型")
    private String machineTypeName;


    @TableField("createdById")
    private String createdById;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedById")
    private String updatedById;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

    @TableField("deletedById")
    private String deletedById;

    @TableField("deletedAt")
    private LocalDateTime deletedAt;

    @TableField("deleted")
    @ApiModelProperty(value = "是否删除(0 否；1 是)")
    @TableLogic(value = "0", delval = "1")
    private Boolean deleted;

    @TableField("isSync")
    @ApiModelProperty(value = "是否由主机厂同步过来的农机数据 0 否；1 是")
    private Boolean isSync;

    @TableField("workWidth")
    @ApiModelProperty(value = "工作宽度")
    private BigDecimal workWidth;

}
