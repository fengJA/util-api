package com.fj.redis.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CanDataDto {
    @ApiModelProperty(value = "农机类型ID")
    private String id;

    @ApiModelProperty(value = "农机名称")
    private String machineName;

    @ApiModelProperty(value = "农机车牌")
    private String plateNumber;
}
