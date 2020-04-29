package com.aliyun.aliyundemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件上传返回vo
 */
@Data
@ApiModel(value = "FileUploadResponseVo", description = "上传文件响应Vo")
public class FileUploadResponseVo {
    @ApiModelProperty(value = "上传文件名称")
    private String sourceFileName;
    @ApiModelProperty(value = "远程存储文件名称")
    private String remoteFileName;
    @ApiModelProperty(value = "文件类型（1：图片 2：视频 3：word 4：Excel 5：PDF 6：PowerPoint）")
    private Integer fileType;
    @ApiModelProperty(value = "文件链接")
    private String fileUrl;
    @ApiModelProperty(value = "视频时长")
    private Long videoDuration;
    @ApiModelProperty(value = "图片宽度")
    private Integer width;
    @ApiModelProperty(value = "图片高度")
    private Integer height;
}
