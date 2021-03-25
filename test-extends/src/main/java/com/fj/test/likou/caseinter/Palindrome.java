package com.fj.test.likou.caseinter;

import java.util.HashSet;

/**
 * 判断一个字符串是否是回文串的一种
 */
public class Palindrome {
    public static void main(String[] args) {
//        String oldStr = "qwer";
//        String str = "rewq";
//        char[] chars = str.toCharArray();
//        char[] newStr = new char[str.length()];
//        int newNum = 0;
//        for (int i = str.length() - 1; i >= 0; i--) {
//            newStr[newNum] = chars[i];
//            newNum ++;
//        }
//        if (oldStr.equals(new String(newStr))){
//            System.out.println("true");
//        }
        boolean qwer = canPermutePalindrome("中文");
        System.out.println(qwer);
    }

    public static boolean canPermutePalindrome(String s) {
        if(s == null){
            return false;
        }
        char[] chars = s.toCharArray();
        HashSet<Object> set = new HashSet<>();
        for (char c : chars){
            if (set.contains(c)){
                set.remove(c);
            }else {
                set.add(c);
            }
        }
        return set.size() <= 1 ? true : false;
    }
}
