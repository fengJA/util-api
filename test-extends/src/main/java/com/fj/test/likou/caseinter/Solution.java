package com.fj.test.likou.caseinter;

import java.util.HashSet;
import java.util.Objects;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 */
public class Solution {
    public static void main(String[] args) {
        String str = "";
        if (Objects.isNull(str)){
            System.out.println("false");
        }
        if (str.length() > 100){
            System.out.println("false");
        }
        char[] chars = str.toCharArray();
        HashSet<Object> set = new HashSet<>(str.length());
        for (char c : chars){
            set.add(c);
        }
        if (set.size() == str.length()){
            System.out.println("true");
        }
    }


}
