package com.aliyun.aliyundemo.service.impl;

import com.aliyun.aliyundemo.service.AliyunOssService;
import com.aliyun.aliyundemo.util.AliyunOssClientUtil;
import com.aliyun.aliyundemo.vo.FileUploadResponseVo;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AliyunOssServiceImp implements AliyunOssService {
    @Autowired
    private AliyunOssClientUtil aliyunOssClientUtil;

    @Override
    public List<FileUploadResponseVo> upload(List<MultipartFile> files, Integer fileType) throws IOException, OSSException, ClientException, EncoderException {
        if (files == null || fileType == null){
            throw new NullPointerException("files和 fileType不能为空");
        }
        List<FileUploadResponseVo> uploadFile = aliyunOssClientUtil.upload(files,fileType);
        return uploadFile;
    }
}
