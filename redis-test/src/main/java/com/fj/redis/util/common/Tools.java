package com.fj.redis.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {


    /**
     * 校验手机号
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * 11位手机号-表达式：
     *      ^1\d{10}$  ：^1表示开始位置为1，\d{10}表示后面10位为数字，$表示结束位置
     *      ^1[0-9]{10}$  ：[0-9]{10}表示后面10位为数字
     *      ^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$
     *      ^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$
     *
     *  固定号码-表达式：
     * ^((d{3,4})|d{3,4}-|s)?d{7,14}$
     */
    private static boolean validPhone(String phoneStr){
        String check;
        if (phoneStr.contains("-")){
            System.out.println("qu-hao");
//            check = "^((d{3,4})|d{3,4}-|s)?d{7,14}$";
            check = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        }else {
            check = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$";
        }
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phoneStr);
        if(!matcher.matches()){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "0532-87690265";
        if (validPhone("0086-010-66778899")){
            System.out.println("============");
        }else {
            System.out.println("+++++++++++");
        }
    }
}
