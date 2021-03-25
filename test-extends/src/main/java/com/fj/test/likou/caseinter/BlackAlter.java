package com.fj.test.likou.caseinter;

/**
 * 将空格替换为%20，末尾的空格去掉
 */
public class BlackAlter {
    public static void main(String[] args) {
//        String str = "       ";
//        int sL = 0;
//        int length = str.length();
//        char[] chars = str.toCharArray();
//        while ((sL < length) && (chars[sL] <= ' ')){
//            sL ++;
//        }
//        while ((sL < length) && (chars[length - 1] <= ' ')){
//            length --;
//        }
//        System.out.println(length);
//        if (sL == length){
//
//        }else {
//            str = str.substring(0, length);
//        }
//        String s = str.replaceAll(" ", "%20");
        String s = replaceSpaces("nwmog q k  gW  c    H  DYpIE    Lcz         gV    Bj   vkH X g       l                                                                                        ", 72);
        System.out.println(s);
    }

    public static String replaceSpaces(String S, int length) {
        int sL = 0;
        int aL = S.length();
        char[] chars = S.toCharArray();
        while ((sL < aL) && (chars[sL] <= ' ')){
            sL ++;
        }
        System.out.println(sL);
        while ((sL < aL) && (chars[aL - 1] <= ' ')){
            aL --;
        }
        System.out.println(aL);
        if (sL == aL){
            if (S.length() >= length){
                S = S.substring(0, length);
            }
        }else {
            S = S.substring(0, aL);
        }
        return S.replaceAll(" ", "%20");
    }
}
