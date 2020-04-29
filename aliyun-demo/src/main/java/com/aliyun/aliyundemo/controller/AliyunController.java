package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.service.AliyunOssService;
import com.aliyun.aliyundemo.vo.FileUploadResponseVo;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(value = "AliyunController", tags = "阿里云OSS文件上传接口")
@RestController
@RequestMapping(value = "/api/v1/oss")
public class AliyunController {
    @Autowired
    private AliyunOssService aliyunOssService;

    @ApiOperation(value = "APP-【后端接口】-【发布】-文件上传", notes = "注：Swagger暂不支持批量文件上传，可通过 postman 或 jmeter 测试工具测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件列表，格式：[MultipartFile file1, MultipartFile file2]", dataType = "List<MultipartFile>", required = true),
            @ApiImplicitParam(name = "fileType", value = "文件类型（1：图片 2：视频 3：word 4：Excel 5：PDF 6：PowerPoint）", dataType = "int", required = true)
    })
    @PostMapping(value = "/upload")
    public List<FileUploadResponseVo> upload(List<MultipartFile> files, Integer fileType) throws IOException, OSSException, ClientException {
        return aliyunOssService.upload(files, fileType);
    }
}
