package com.fj.test.service.impl;

import com.fj.test.service.PdfDownService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfDownServiceImpl implements PdfDownService {

    @Override
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置响应格式等
         response.setContentType("application/pdf");
         response.setHeader("Expires", "0");
         response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
         response.setHeader("Pragma", "public");
        Map<String,Object> map = new HashMap<>();
         //设置纸张规格为A4纸
         Rectangle rect = new Rectangle(PageSize.A4);
         //创建文档实例
         Document doc = new Document(rect);

         //添加中文字体
         BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         //设置字体样式
        Font textFont = new Font(bfChinese,8, Font.NORMAL); //正常
         //Font redTextFont = new Font(bfChinese,11,Font.NORMAL,Color.RED); //正常,红色
         Font boldFont = new Font(bfChinese,11,Font.BOLD); //加粗
         //Font redBoldFont = new Font(bfChinese,11,Font.BOLD,Color.RED); //加粗,红色
         Font firsetTitleFont = new Font(bfChinese,22,Font.BOLD); //一级标题
         Font secondTitleFont = new Font(bfChinese,15,Font.BOLD, CMYKColor.BLACK); //二级标题
         Font thirdTitleFont = new Font(bfChinese,10,Font.BOLD, CMYKColor.BLUE); //二级标题
         Font underlineFont = new Font(bfChinese,11,Font.UNDERLINE); //下划线斜体
         //设置字体
         com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD);
         com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);
         com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 7, com.itextpdf.text.Font.BOLD,CMYKColor.BLUE);
         com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
        com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);

        //设置要导出的pdf的标题
         String title = "前装补助申请单" + ".pdf";
        response.setHeader("Content-disposition","attachment; filename=".concat(String.valueOf(URLEncoder.encode(title + ".pdf", "UTF-8"))));
         OutputStream out = response.getOutputStream();
        PdfWriter.getInstance(doc, out);
        doc.open();
//        String filename = response.getHeader("Content-disposition");
        doc.newPage();
         //新建段落
         //使用二级标题 颜色为蓝色
         Paragraph p1 = new Paragraph("前装补助申请单", secondTitleFont);
         //设置行高
         p1.setLeading(0);
         //设置标题居中
         p1.setAlignment(Element.ALIGN_CENTER);
         //将段落添加到文档上
         doc.add(p1);
         //设置一个空的段落，行高为18  什么内容都不显示
         Paragraph blankRow1 = new Paragraph(15f, " ", FontChinese11);
        Paragraph blankRow2 = new Paragraph(10f, " ", FontChinese11);
         doc.add(blankRow1);

         // 前装设备标题
        Paragraph deviceInfo = new Paragraph( "前装设备信息", FontChinese11Bold);
        deviceInfo.setIndentationLeft(18f);
        doc.add(deviceInfo);
        doc.add(blankRow2);

        // 前装设备表格信息
        PdfPTable pdfPTable = new PdfPTable(5);
        float tabWidth[] = {16,40,16,16,40};
        pdfPTable.setWidths(tabWidth);
        pdfPTable.setWidthPercentage(90);
        pdfPTable.setHorizontalAlignment(5);
        Paragraph elements = new Paragraph(1);
        PdfPCell pCell1 = new PdfPCell(new Paragraph("设备Id：", textFont));
        PdfPCell pCell2 = new PdfPCell(new Phrase("", textFont));
        PdfPCell pCell5 = new PdfPCell(new Phrase(" ", textFont));
        PdfPCell pCell3 = new PdfPCell(new Paragraph("卡Id：", textFont));
        PdfPCell pCell4 = new PdfPCell(new Phrase("我就是卡Id-76767", textFont));
//        pCell2.setCalculatedHeight(5);
//        pCell4.setMinimumHeight(5);
        pdfPTable.addCell(pCell1).setBorder(15);
        pdfPTable.addCell(pCell2).setBorder(15);
        pdfPTable.addCell(pCell5).setBorder(0);
        pdfPTable.addCell(pCell3).setBorder(15);
        pdfPTable.addCell(pCell4).setBorder(15);
        doc.add(pdfPTable);
        doc.add(blankRow2);

        // 添加图片
//        PdfPTable machineInfo = new PdfPTable(3);
//        float machineWidth[] = {4,8,60};
//        machineInfo.setWidths(machineWidth);
////        machineInfo.setTotalWidth(20f);
//        machineInfo.setHorizontalAlignment(0);

//        machineInfo.setWidthPercentage(0);

//        Image image =Image.getInstance("D:\\360极速浏览器下载\\刘亦菲\\刘亦菲.jpg");
////        image.setAlignment(Image.ALIGN_CENTER);
//        image.scalePercent(3f);//依照比例缩放
//        PdfPCell mahineCell = new PdfPCell(new Paragraph("农机信息：", FontChinese11Bold));
//        PdfPCell mahineCel2 = new PdfPCell(new Paragraph("  ", FontChinese11Bold));
//        PdfPCell mahineCel3 = new PdfPCell(image);
//        machineInfo.addCell(mahineCel3).setBorder(0);
//        machineInfo.addCell(mahineCell).setBorder(0);
//        machineInfo.addCell(mahineCel2);
//        doc.add(machineInfo);
//        doc.add(blankRow2);

        // 前装设备表格信息

        PdfPTable virfyInfo = new PdfPTable(4);
        float virfyWidth[] = {30,30,30,30};
        virfyInfo.setWidths(virfyWidth);
        virfyInfo.setHorizontalAlignment(0);

        virfyInfo.addCell(new PdfPCell(new Paragraph("证书编号：", textFont))).setBorder(15);
//        virfyInfo.addCell(new PdfPCell(new Paragraph("设备Id：", FontChinese11Bold))).setBorder(15);
        virfyInfo.addCell(new PdfPCell(new Paragraph("No-9876：", textFont))).setBorder(15);
        virfyInfo.addCell(new PdfPCell(new Paragraph("证书状态：", textFont))).setBorder(15);
//        virfyInfo.addCell(new PdfPCell(new Paragraph("卡Id：", textFont))).setBorder(0);
//        virfyInfo.addCell(new PdfPCell(new Paragraph("我就是卡Id-76767", textFont))).setBorder(0);
        virfyInfo.addCell(new PdfPCell(new Paragraph("已注销", textFont))).setBorder(15);
        doc.add(virfyInfo);
        doc.add(blankRow2);



        doc.close();

    }
}
