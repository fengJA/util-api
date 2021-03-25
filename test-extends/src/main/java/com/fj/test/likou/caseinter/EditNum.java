package com.fj.test.likou.caseinter;

/**
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
 * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑
 */
public class EditNum {
    public static void main(String[] args) {
//        boolean b = oneEditAway("teacher", "taches");
        boolean a = edit("a", "ab");
        System.out.println(a);
    }

    public static boolean oneEditAway(String first, String second) {
        int fL = first.length();
        int sL = second.length();
        char[] fCh = first.toCharArray();
        char[] seCh = second.toCharArray();
        if ((fL - sL) > 1 || (sL - fL) > 1){
            return false;
        }
        if (fL == 1 || sL == 1){
            return true;
        }
        if (fL < sL){
            return exchange(fCh, seCh, fL);
        }else if (fL > sL){
            return exchange(seCh, fCh, sL);
        }else {
            int num = 0;
            for (int i = 0; i < fL; i++) {
                if (num > 1){
                    return false;
                }
                if (fCh[i] != seCh[i]){
                    num ++;
                }
            }
            if (num > 1){
                return false;
            }
            return true;
        }
    }

    private static boolean exchange(char[] fCh, char[] seCh, int fL){
        int j = 0;
        int num = 0;
        for (int i = 0; i < fL; i++) {
            if (num > 1){
                return false;
            }
            if (fCh[i] == seCh[j]){
                j++;
            }else {
                num ++;
                System.out.println(i);
                j += 1;
                if (j <= seCh.length -1 && fCh[i] == seCh[j]){
                    j++;
                    continue;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean edit(String first, String second){
        int len = first.length()-second.length();
        if (len>1||len<-1) {
            return false;
        }
        int count=1;
        for (int i = 0,j=0; i < first.length()&&j < second.length(); i++,j++) {
            if (first.charAt(i)!=second.charAt(j)) {
                if (len==1) { //second要不要添加一个字符
                    j--;
                }else if (len==-1) { //second要不要删除一个字符
                    i--;
                }
                count--;
            }
            if (count<0) {//最多编辑一次
                return false;
            }
        }
        return true;
    }
}
