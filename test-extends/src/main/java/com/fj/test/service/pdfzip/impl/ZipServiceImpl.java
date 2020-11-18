package com.fj.test.service.pdfzip.impl;

import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.fj.test.service.pdfzip.ZipService;
import com.fj.test.utils.FileUtils;
import com.fj.test.utils.ZipUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
public class ZipServiceImpl implements ZipService {
    private static String separator = "\\";

    // 保存路径
    private static String localPath = "D:\\temp\\PDF审核";

    private static String fileUrl = "D:\\360极速浏览器下载\\刘亦菲";




    @Override
    public void zipPdf() {
        // 上传文件到本地
        uploadURL(localPath,fileUrl,"刘亦菲.jpg");

        zipDocment(fileUrl, localPath);

//        getFile();
    }

    /**
     * 返给前端并删除本地文件
     * @param zipFile
     * @param response
     * @param downloadName 下载的文件的名称（自己取）
     */
    private void getFile(File zipFile, HttpServletResponse response, String downloadName){
        try{
            // 把压缩文件流返回到前端
            InputStream inputData = new FileInputStream(zipFile);
            FileUtils.downloadFile(response, downloadName, inputData);
        }catch (IOException e){
//            logger.error("返回出错：", e);
        }
// 删除本地所有文件
        FileUtils.deleteFile(zipFile);
    }

    /**
     * 进行压缩ZIP
     * @param fileUrl
     * @param url
     */
    private void zipDocment(String fileUrl, String url){
        String downloadName = "冯佳在测试.zip";
        File zipFile = new File(localPath + separator + downloadName);// 压缩的zip文件和地址
        File localPath = new File(url); // 需要压缩的文件夹地址
        FileOutputStream fos1 = null;
        try{
            fos1 = new FileOutputStream(zipFile);
        }catch (FileNotFoundException e){
//            logger.error("处理失败：",e);
        }
        ZipUtils.toZip(localPath, fos1, true);// 压缩文件
    }


    /**
     * 下载资源到本地
     * @param fileUrl 文件地址
     * @param localPath 保存路径
     */
    public void uploadURL(String fileUrl, String localPath, String fileName){
        // 中文链接转码
        fileUrl = FileUtils.cnToEncode(fileUrl);

        HttpURLConnection connection = null;
        DataInputStream in = null;
        try {
            URL url = new URL(fileUrl);/*将网络资源地址传给,即赋值给url*/
            /*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
            connection = (HttpURLConnection)url.openConnection();
            in = new DataInputStream(connection.getInputStream());
            /*此处也可用BufferedInputStream与BufferedOutputStream*/

            // 文件夹不存在，创建
            File file = new File(localPath);
            if(!file.exists()){
                file.mkdirs();
            }

            try(FileOutputStream fileOutputStream = new FileOutputStream(new File(file+ separator +fileName));
                DataOutputStream out = new DataOutputStream(fileOutputStream)) {
                /*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
                byte[] buffer = new byte[4096];
                int count = 0;
                /*将输入流以字节的形式读取并写入buffer中*/
                while ((count = in.read(buffer)) > 0) {
                    out.write(buffer, 0, count);
                }
            }catch (IOException e){
//                logger.error("处理文件流出错：", e);
            }
        } catch (Exception e) {
//            logger.error("资源地址："+fileUrl);
//            logger.error("下载资源出错：", e);
        }finally {
//            IOUtils.closeQuietly(in);
            if(connection !=null)
                connection.disconnect();
        }
    }


//    private void zip
















    // 生成二维码---未成功
    public static void main(String[] args) throws Exception{
//        getQrcode("https://lunz-moyou.oss-cn-chengdu.aliyuncs.com/moyou-apk/%E6%91%A9%E5%8F%8B-%E9%A2%84%E5%8F%91%E5%B8%83-2.0.0.016-202007301731.apk");


//        String qrUrl = "https://lunz-moyou.oss-cn-chengdu.aliyuncs.com/moyou-apk/%E6%91%A9%E5%8F%8B-%E9%A2%84%E5%8F%91%E5%B8%83-2.0.0.016-202007301731.apk";//扫描二维码跳转的链接
//        String note = "摩友";//二维码下方展示的字
//        File logoFile = new File("D:\\temp\\liu.jpg");//logo图片路径
//        File codeFile = new File("E:\\aa");//生成后图片的输出地址
//        drawLogoQRCode(logoFile, codeFile, qrUrl, note);


//        File file = new File("D:\\123.rar");
        File file = new File("D:\\11\\");
        if (file.exists()){
            deleteFile(file);
        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }



    public static void getQrcode(String content) throws Exception {
        OutputStream stream = System.out; ;
       /* Long qrid=Long.parseLong(content);
        Ticket ticket=ticketRepository.findByTicketId(qrid);
       if (ticket!=null) {*/
        try {
            stream.write(content.getBytes());
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //编码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //边框距
            hints.put(EncodeHintType.MARGIN, 0);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            MatrixToImageWriter.writeToStream(bm, "png", stream);



            System.out.println("hints----->" + hints);
            System.out.println("bm----->" + bm);
            System.out.println("stream----->" + stream);

        } catch (Exception e) {
            e.getStackTrace();

        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    public static String drawLogoQRCode(File logoFile, File codeFile, String qrUrl, String note) {//图片文件   二维码储存地址  网页路径     二维码说明
        int WIDTH = 200;
        int HEIGHT = 200;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //边框距
        hints.put(EncodeHintType.MARGIN, 0);
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? 0xFFFFFFFF : 0xFF000000);
                }
            }
            int width = image.getWidth();
            int height = image.getHeight();
            if (Objects.nonNull(logoFile) && logoFile.exists()) {
                // 构建绘图对象
                Graphics2D g = image.createGraphics();
                // 读取Logo图片
                BufferedImage logo = ImageIO.read(logoFile);
                // 开始绘制logo图片
                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
                g.dispose();
                logo.flush();
            }
            // 自定义文本描述
            if (!note.isEmpty()) {
                // 新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                // 画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                // 画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("楷体", Font.BOLD, 30)); // 字体、字型、字号
                int strWidth = outg.getFontMetrics().stringWidth(note);
                if (strWidth > 399) {
                    // //长度过长就截取前面部分
                    // 长度过长就换行
                    String note1 = note.substring(0, note.length() / 2);
                    String note2 = note.substring(note.length() / 2, note.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(note1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(note2);
                    outg.drawString(note1, 200 - strWidth1 / 2, height + (outImage.getHeight() - height) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); // 字体、字型、字号
                    outg2.drawString(note2, 200 - strWidth2 / 2,outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(note, 200 - strWidth / 2, height + (outImage.getHeight() - height) / 2 + 12); // 画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            image.flush();
            ImageIO.write(image, "png", codeFile); // TODO
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }


}
