package com.aliyun.aliyundemo.util;


import com.aliyun.aliyundemo.enums.FileTypeEnum;
import com.aliyun.aliyundemo.vo.FileUploadResponseVo;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ValidationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class AliyunOssClientUtil {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * @param files    文件列表
     * @param fileType 文件类型（1：图片 2：视频 3：word 4：Excel 5：PDF 6：PowerPoint）
     * @return         响应列表
     */
    public List<FileUploadResponseVo> upload(List<MultipartFile> files, Integer fileType) throws EncoderException {
        log.info("开始上传的时间：" + getNowTime());
        OSS ossClient = getOssClient();
        // 响应列表
        List<FileUploadResponseVo> list = new ArrayList<>();
        // 上传原文件名称
        String sourceFileName;
        // 文件夹
        String folder;
        try{
            for (MultipartFile multipart : files) {
                sourceFileName = multipart.getOriginalFilename();
                if (sourceFileName == null){
                    throw new NullPointerException("文件名称为空!");
                }
                // 获取文件夹
                folder = getFolderByType(fileType);
                // 将MultipartFile转换为File
                File file = multipartFileToFile(multipart);

                // 上传文件
                FileUploadResponseVo responseVo = uplaodFile(sourceFileName,folder,file,ossClient,fileType);

                switch (FileTypeEnum.getFileType(fileType)){
                    case IMAGE:
                        BufferedImage image = getImage(file);
                        responseVo.setHeight(image.getHeight());
                        responseVo.setWidth(image.getWidth());
                        break;
                    case VIDEO:
                        log.info("开始获取"+ sourceFileName +"视频时长，时间：" + getNowTime());
                        responseVo.setVideoDuration(getDuration(file));
                        log.info("开始截帧处理"+ sourceFileName +"，时间：" + getNowTime());
                        FileUploadResponseVo res = cutImgOfVideo(folder, sourceFileName, responseVo.getFileUrl(), ossClient);
                        log.info("截帧处理"+ sourceFileName +"完毕，时间：" + getNowTime());
                        list.add(res);
                        responseVo.setWidth(res.getWidth());
                        responseVo.setHeight(res.getHeight());
                        break;
                }
                deleteFile(file);
                list.add(responseVo);
            }
        } finally {
            ossClient.shutdown();
        }
        return list;
    }


    /**
     * 截取视频封面图片
     *
     * @param folder    视频所在文件夹
     * @param sourceFileName 视频文件名称
     * @param fileUrl  视频链接地址
     * @param ossClient oss对象
     * @return          响应
     */
    private FileUploadResponseVo cutImgOfVideo(String folder, String sourceFileName, String fileUrl, OSS ossClient) {
        String remoteFileName = getToday() + "/" + UUID.randomUUID() + "-" + sourceFileName.substring(0, sourceFileName.lastIndexOf(".")) + ".png";
        String imgPath = folder + "snapshot/" + remoteFileName;
        String imgRmoteUrl = "http://".concat(bucket).concat(".").concat(endpoint).concat("/").concat(imgPath);

        // 截取图片参数
        String style = "x-oss-process=video/snapshot,t_1,f_png,ar_auto,m_fast";
        String imgSourceUrl = fileUrl + "?" + style;

        // 上传截图
        File file = new File(remoteFileName.substring(remoteFileName.indexOf("/")));
        inputStreamToFile(getImageStream(imgSourceUrl), file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, imgPath, file);
        ossClient.putObject(putObjectRequest);
        BufferedImage bi = getImage(file);
        deleteFile(file);
        return new FileUploadResponseVo(sourceFileName, remoteFileName, FileTypeEnum.IMAGE.getCode(), imgRmoteUrl, null, bi.getWidth(), bi.getHeight());
    }

    /**
     * 删除文件
     * @param file
     */
    private void deleteFile(File file) {
        if (file.exists()) {
            // 睡眠100毫秒后删除文件（不休眠可能无法删除）
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("删除临时文件：" + file.getAbsoluteFile() + "结果：" + file.delete());
        }
    }

    /**
     * 获取图片流
     * @param url 图片路径
     * @return    图片流
     */
    private InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取视频时长
     * @param file
     * @return
     */
    private Long getDuration(File file) throws EncoderException {
        Encoder encoder = new Encoder();
        MultimediaInfo info = encoder.getInfo(file);
        long videoTime = info.getDuration() / 1000; // 总共多少秒
        int hour = (int) videoTime / 3600;
        int minute = (int) ((videoTime % 3600) / 60);
        int second = (int) (videoTime - hour * 3600 - minute * 60);
        log.info("获取到视频时长：" + videoTime + "秒，时间：" + getNowTime());
        return videoTime;
    }

    /**
     * 从文件流中获取文件
     * @param file
     * @return
     */
    private BufferedImage getImage(File file) {
        try {
            BufferedImage read = ImageIO.read(file);
            if(read == null){
                throw new ValidationException("未获取到图片信息");
            }
            return read;
        } catch (IOException e) {
            throw new ValidationException("远程获取图片异常");
        }
    }

    private FileUploadResponseVo uplaodFile(String sourceFileName, String folder, File file, OSS ossClient, Integer fileType) {
        // 存储至OSS的文件名,一般用主键生成器
        String remoteFileName = getToday() + "/" + UUID.randomUUID() + "-" + sourceFileName;
        // 存储至OSS文件路径
        String path = folder + remoteFileName;
        // 存储的远程链接
        String fileUrl = "http://".concat(bucket).concat(".").concat(endpoint).concat("/").concat(path);
        log.info("上传文件："+ sourceFileName +"开始，时间：" + getNowTime());
        PutObjectRequest request = new PutObjectRequest(bucket, path, file);
        ossClient.putObject(request);
        log.info("上传文件："+ sourceFileName +"结束，时间：" + getNowTime());

        return new FileUploadResponseVo(sourceFileName,remoteFileName,fileType,fileUrl,null,null,null);
    }

    /**
     * MultipartFile 类型转 File 类型
     * @param files
     * @return
     */
    private File multipartFileToFile(MultipartFile files) {
        File file = null;
        InputStream stream = null;
        try {
            stream = files.getInputStream();
            // 使用UUID不太准确，一般用主键生成器来搞;创建一个新的文件file，用来存储stream
            file = new File(UUID.randomUUID().toString() + files.getOriginalFilename());
            inputStreamToFile(stream,file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stream != null){
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * InputStream 转 File
     * @param stream
     * @param file
     */
    private void inputStreamToFile(InputStream stream, File file) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int fileLength;
            byte[] bytes = new byte[8192];
            while ((fileLength = stream.read(bytes,0,8192)) != -1){
                outputStream.write(fileLength);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null){
                    outputStream.close();
                }
                if (stream != null){
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据文件类型判断所属文件夹
     * @param fileType  文件类型
     * @return          文件夹名称
     * @throws Exception
     */
    private String getFolderByType(Integer fileType) {
        String folder;
        switch (FileTypeEnum.getFileType(fileType)){
            case IMAGE:
                folder = "images/";
                break;
            case VIDEO:
                folder = "videos/";
                break;
            case WORD:
                folder = "office/word/";
                break;
            case EXCEL:
                folder = "office/excel/";
                break;
            case PDF:
                folder = "office/pdf/";
                break;
            case PPT:
                folder = "office/powerpoint/";
                break;
            default:
                throw new ValidationException("错误的文件类型参数");
        }
        return folder;
    }

    // 创建OSS客户端对象
    private OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
    }

    private String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private String getToday () {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
