package com.fj.test.likou.caseinter;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
 */
public class ZipStr {

    public static void main(String[] args) {
        String s = compressString(" ");
        System.out.println(s);
    }


    public static String compressString(String S) {
        if (S == null || S.length() <= 0){
            return S;
        }
        int num = 1;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if ((i + 1) < S.length()){
                if (S.charAt(i) == S.charAt(i+1)){
                    num ++;
                }else {
                    str = str.append(S.charAt(i)).append(num);
                    num = 1;
                }
            }else {
                str = str.append(S.charAt(i)).append(num);
            }
        }
        return (str.length() < S.length()) ? str.toString() : S;
    }
}
