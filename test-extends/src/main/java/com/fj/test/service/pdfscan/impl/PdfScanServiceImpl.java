package com.fj.test.service.pdfscan.impl;

import com.fj.test.entity.PdfScanVo;
import com.fj.test.service.pdfscan.PdfScanService;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfTextBoxFieldWidget;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


@Service
public class PdfScanServiceImpl implements PdfScanService {

    @Override
    public void scanPdf() {
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("D:\\aa\\response3.pdf");



        //获取表单域
        PdfFormWidget formWidget = (PdfFormWidget)pdf.getForm();
        PdfScanVo pdfScanVo = new PdfScanVo();

        //遍历表单域控件集合并提取所有表单的值
        for (int i = 0; i < formWidget.getFieldsWidget().getCount(); i++) {
            PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
            if (field instanceof PdfTextBoxFieldWidget) {
                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
                //获取文本框的文本
                String text = textBoxField.getText();
                if (text.equals("设备Id：")){
                    continue;
                }else {
                    pdfScanVo.setCertificateNo(text);
                }

            }

        }
    }

    @Override
    public void uploadPdf(MultipartFile file) throws Exception{
        if (!file.isEmpty()){
            // 获取文件原文件名
            String originalFilename = file.getOriginalFilename();
            if (originalFilename.isEmpty()) {
                throw new Exception("文件名称为空");
            }
//            File pdfDocument = multipartFileToFile(file);\

            String tmpPath = "D:\\temp\\PDF审核\\";

            File folder = new File(tmpPath);
            if (!folder.exists() && !folder.isDirectory()){
                folder.mkdirs();
            }

            String fileName = executeUpload(tmpPath,file);
            System.out.println("我的文件是---" + fileName);

            if (fileName.isEmpty()){
                throw new Exception("扫描文件失败");
            }

            // 可以采用阿里云的OCR识别，就不用自己写了
            viewPDF(fileName);
        }
    }

    /**
     * 文件扫描
     * @param fileName
     * @throws Exception
     */
    public void viewPDF(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) { // 文件不存在，则 从FTP下载文件到本地
            throw new Exception("扫描文件失败");
        }

        //读取pdf文件内容-代码实现
		try {
        PDDocument document = PDDocument.load(file);
        document.getClass();
        StringBuffer stringBuffer = new StringBuffer();
        String st = new String();
        if(!document.isEncrypted()) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            PDFTextStripper textStripper = new PDFTextStripper();
            String exposeContent = textStripper.getText(document);
            String[] content = exposeContent.split("\\n");
            // 将exposeContent用正则表达（根据\r 或者\r\n）是的形式分割；存放在map中，形成简直对的形式
            Map<String,String> map = new HashMap<>();




            for(String line:content) {
                stringBuffer.append(line);
                st.concat(line);
            }
            System.out.println("PDF文件是---" + exposeContent);
            System.out.println("----------------" );
            System.out.println("----------------" );
            System.out.println("----------------" );
            System.out.println("----------------" );

        }
//            System.out.println("PDF文件是---" + stringBuffer);

        } catch (Exception e) {

        }
    }


    /**
     * 上传文件
     * @param uploadDir
     * @param file
     * @return
     * @throws Exception
     */
    public String executeUpload(String uploadDir,MultipartFile file) throws Exception
    {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String filename = UUID.randomUUID() + suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);

        return uploadDir + filename;
    }

}
