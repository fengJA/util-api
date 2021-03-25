package com.fj.test.likou.caseinter;

/**
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
 * （比如，waterbottle是erbottlewat旋转后的字符串）,从字符串首位截取一点放到末尾
 */
public class RoStr {

    public static void main(String[] args) {
        System.out.println(isFlipedString("aac", "aba"));
    }

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        String s3 = s2 + s2;

        return s3.contains(s1) ? true : false;
    }

}
