package com.fj.test.likou.caseinter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 */
public class StrExchange {
    public static void main(String[] args) {
        String str1 = "中文", str2 = "文中";
        if (Objects.isNull(str1) || Objects.isNull(str2)){
            System.out.println("false");
        }
        if (str1.length() > 100 || str2.length() > 100){
            System.out.println("false");
        }
        if (str1 == str2){
            System.out.println("false");
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        for (int i = 0; i < chars1.length; i++) {
            System.out.print(chars1[i]);
        }
        System.out.println();
        Arrays.sort(chars2);
        for (int i = 0; i < chars2.length; i++) {
            System.out.print(chars2[i]);
        }
        if (new String(chars1).equals(new String(chars2))){
            System.out.println();
            System.out.println("true");
        }

    }
}
