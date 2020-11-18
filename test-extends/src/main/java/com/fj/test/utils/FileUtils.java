package com.fj.test.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 操作文件工具类
 */
public class FileUtils {

    /**
     * 将字符串中的中文进行编码
     * @param s
     * @return
     */
    public static String cnToEncode(String s){
        char[] ch = s.toCharArray();
        String result = "";
        for (int i = 0; i < ch.length; i++){
            char temp = ch[i];
            if (isChinese(temp)){
                try{
                    String encode = URLEncoder.encode(String.valueOf(temp), "utf-8");
                    result = result + encode;
                }catch (UnsupportedEncodingException e){
//                    logger.error("处理字符串出错："+s, e);
                }
            }else{
                result = result + temp;
            }
        }
        return result;
    }

    /**
     * 判断字符是否为汉字
     *
     * @param c
     * @return
     */
    private static boolean isChinese(char c){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION){
            return true;
        }
        return false;
    }



    /**
     * 下载文件流
     * @param response
     * @param fileName
     * @param inputData
     * @throws IOException
     */
    public static void downloadFile(HttpServletResponse response, String fileName, InputStream inputData) throws IOException {

        BufferedInputStream bins=new BufferedInputStream(inputData);//放到缓冲流里面
        OutputStream outs=response.getOutputStream();//获取文件输出IO流
        BufferedOutputStream bouts=new BufferedOutputStream(outs);
        response.setContentType("application/x-download");
        response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        //开始向网络传输文件流
        while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
            bouts.write(buffer, 0, bytesRead);
        }
        bouts.flush();//这里一定要调用flush()方法
        inputData.close();
        bins.close();
        outs.close();
        bouts.close();

    }

    /**
     * 删除文件
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    deleteFile(subFile);
                }
            }
            if(file.exists())
                file.delete(); // 删除文件夹
        } else {
            if(file.exists())
                file.delete();
        }
    }
}
