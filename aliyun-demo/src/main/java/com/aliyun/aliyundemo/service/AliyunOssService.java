package com.aliyun.aliyundemo.service;

import com.aliyun.aliyundemo.vo.FileUploadResponseVo;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AliyunOssService {
    /**
     * 批量文件上传
     * @param files 文件列表
     * @return      文件地址列表
     */
    /**
     * 批量文件上传
     * @param files    文件列表
     * @param fileType 文件类型（1：图片 2：视频 3：word 4：Excel 5：PDF 6：PowerPoint）
     * @return         文件地址列表
     * @throws IOException
     * @throws OSSException
     * @throws ClientException
     */
    List<FileUploadResponseVo> upload(List<MultipartFile> files, Integer fileType) throws IOException, OSSException, ClientException, EncoderException;

}
