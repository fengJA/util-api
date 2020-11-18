package com.fj.test.service.pdfscan.impl;

import com.fj.test.entity.CertificateInfoVo;
import com.fj.test.entity.PdfScanVo;
import com.fj.test.service.pdfscan.PdfScanService;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfTextBoxFieldWidget;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


@Service
public class PdfScanServiceImpl implements PdfScanService {

    @Override
    public void scanPdf() {
//        PdfDocument pdf = new PdfDocument();
//        pdf.loadFromFile("D:\\aa\\response3.pdf");

        try {
            PdfDocument doc = new PdfDocument();
            doc.loadFromFile("D:\\c14cad67-7ff6-4868-b024-b5486007e9ab.pdf");
            StringBuilder sb = new StringBuilder();
            PdfPageBase page;
            for (int i = 0; i < doc.getPages().getCount(); i++) {
                //获取每一行的page对象
                page = doc.getPages().get(i);
                sb.append(page.extractText(true));
                //这个if中是图片的解析输出
                if (page.extractImages() != null) {
                    for (BufferedImage image : page.extractImages()) {
                        if (image != null) {
                            //指定输出图片名，指定图片格式,后缀自己换
                            File output = new File("E:\\pdf\\img.png");
                            ImageIO.write(image, "PNG", output);
                        }
                    }
                }
            }
            System.out.println(sb);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




//        //获取表单域
//        PdfFormWidget formWidget = (PdfFormWidget)pdf.getForm();
//        PdfScanVo pdfScanVo = new PdfScanVo();
//
//        //遍历表单域控件集合并提取所有表单的值
//        for (int i = 0; i < formWidget.getFieldsWidget().getCount(); i++) {
//            PdfField field = (PdfField) formWidget.getFieldsWidget().getList().get(i);
//            if (field instanceof PdfTextBoxFieldWidget) {
//                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
//                //获取文本框的文本
//                String text = textBoxField.getText();
//                if (text.equals("设备Id：")){
//                    continue;
//                }else {
//                    pdfScanVo.setCertificateNo(text);
//                }
//
//            }
//
//        }
    }

    @Override
    public void uploadPdf(MultipartFile file) throws Exception{
        if (!file.isEmpty()){
//            // 获取文件原文件名
//            String originalFilename = file.getOriginalFilename();
//            if (originalFilename.isEmpty()) {
//                throw new Exception("文件名称为空");
//            }
////            File pdfDocument = multipartFileToFile(file);\
//
//            String tmpPath = "D:\\temp\\PDF审核\\";
//
//            File folder = new File(tmpPath);
//            if (!folder.exists() && !folder.isDirectory()){
//                folder.mkdirs();
//            }
//
//            String fileName = executeUpload(tmpPath,file);
//            System.out.println("我的文件是---" + fileName);
//
//            if (fileName.isEmpty()){
//                throw new Exception("扫描文件失败");
//            }

            File files = exchangeFile(file);

            // 可以采用阿里云的OCR识别，就不用自己写了
            viewPDF(files);
        }
    }

    /**
     * 文件扫描
     * @param fileName
     * @throws Exception
     */
    public void viewPDF(File fileName) throws Exception {
//        File file = new File(fileName);
        if (!fileName.exists()) { // 文件不存在，则 从FTP下载文件到本地
            throw new Exception("扫描文件失败");
        }

        //读取pdf文件内容-代码实现
		try {
        PDDocument document = PDDocument.load(fileName);
        document.getClass();
        StringBuffer stringBuffer = new StringBuffer();
        String st = new String();
        if(!document.isEncrypted()) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setSortByPosition(true);
            String exposeContent = textStripper.getText(document);
            String[] content = exposeContent.split("\\n");
            // 将exposeContent用正则表达（根据\r 或者\r\n）是的形式分割；存放在map中，形成简直对的形式
            Map<String,String> map = new HashMap<>();
            CertificateInfoVo certificateInfoVo = new CertificateInfoVo();
            int j = 0;
            String[] b = new String[content.length];
            for (int i = 0; i < content.length; i++) {
                if (!content[i].equals("\\r")){
                    b[j++] = content[i];
                }
            }

            for (int i = 0; i < content.length; i++) {
                if (( 1< i && i < 8 )||( i > 8 && i < 14)){
                    String[] detail = content[i].split("\\s");
                    certificateInfoVo.setCertificateNumber(detail[1]);
                    certificateInfoVo.setCertificateStatus(detail[3]);
                }
            }

            if (content.length > 27){
                String s2 = content[2];
                System.out.println(s2);
                String[] s = s2.split("\\s");
                for (int i = 0; i < s.length; i++) {
                    System.out.println("=======");
                    System.out.println(s[i]);
                }

                String s10 = content[10];
                System.out.println(s10);
                String[] s101 = s10.split("\\s");
                for (int i = 0; i < s101.length; i++) {
                    System.out.println("=======");
                    System.out.println(s[i]);
                }

                String s18 = content[18];
                System.out.println(s18);
                String[] s181 = s18.split("\\s");
                for (int i = 0; i < s181.length; i++) {
                    System.out.println("=======");
                    System.out.println(s[i]);
                }
            }

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


    @Override
    public void itextScanPdf(MultipartFile multiFile) {
        // 将MultipartFile转换为File
        File file = exchangeFile(multiFile);

        if (Objects.isNull(file)){
            System.out.println("转换文件失败");
        }
        String pageContent = "";
        try {
            FileInputStream inputStream = new FileInputStream(file);
            PdfReader reader = new PdfReader(inputStream);
            int pageNum = reader.getNumberOfPages();
            for(int i=1;i<=pageNum;i++){
                pageContent += PdfTextExtractor.getTextFromPage(reader, i);//读取第i页的文档内容
            }
            System.out.println(pageContent);
//            writer.write(pageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
//            writer.close();
        }

    }

    /**
     * 将MultipartFile转换为File
     * @param multiFile
     */
    private File exchangeFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String s = "证书编号 部 20160024                       证  书  状  态                             注  销 ";
//        String s1 = s.replaceAll(" ", "");
//        int i1 = s1.indexOf("号");
//        int i2 = s1.indexOf("证书状态");
//        System.out.println(i1);
//        System.out.println(i2);
//        String certificateNumber = s1.substring(s1.indexOf("号") + 1, s1.indexOf("证书状态") - 1);
//        String certificateStatus = s1.substring(s1.indexOf("态") + 1);
//
//
//        System.out.println(certificateNumber);
//        System.out.println(certificateStatus);
//        System.out.println(s1);

//        String a[]={"a","b","c","d","e"};
//        String b[] = new String[a.length];
//        int j = 0;
//        for(int i=0;i< a.length; i++){
//            if(!a[i].equals("c")){
//                b[j++] = a[i];
////                for (int j = 0; j < a.length; j++) {
////                    b[j]=a[i];
////                }
//            }
//        }
//        for (int i = 0; i < b.length ; i++) {
//            System.out.println(b[i]);
//        }


        String a[] = {"a","\r","b"};
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

}
