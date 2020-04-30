package com.aliyun.aliyundemo.enums;

/**
 * 文件类型枚举类
 */
public enum FileTypeEnum {
    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    WORD(3,"WORD"),
    EXCEL(4,"EXCEL"),
    PDF(5,"PDF"),
    PPT(6,"PPT");

    private int code;
    private String msg;


    FileTypeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static FileTypeEnum getFileType(int value){
        for (FileTypeEnum fileType : values()) {
            if (fileType.getCode() == value){
                return fileType;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }}
