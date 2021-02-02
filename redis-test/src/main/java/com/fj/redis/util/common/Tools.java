package com.fj.redis.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    private final static int NEW_CARD_NUMBER_LENGTH = 18;

    private final static char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7',
            '6', '5', '4', '3', '2' }; // 18位身份证中最后一位校验码

    private final static int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1,
            6, 3, 7, 9, 10, 5, 8, 4, 2 };// 18位身份证中，各个数字的生成校验码时的权值


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

    /**
     * 身份证号码的验证
     * @return
     */
    private static boolean validIdCard(String cardNumber){
        int NEW_CARD_NUMBER_LENGTH = 18;

        int OLD_CARD_NUMBER_LENGTH = 15;

        char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7',
                '6', '5', '4', '3', '2' }; // 18位身份证中最后一位校验码

        int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1,
                6, 3, 7, 9, 10, 5, 8, 4, 2 };// 18位身份证中，各个数字的生成校验码时的权值

        boolean result = true;
        result = result && (null != cardNumber); // 身份证号不能为空
        result = result && NEW_CARD_NUMBER_LENGTH == cardNumber.length(); // 身份证号长度是18(新证)
        // 身份证号的前17位必须是阿拉伯数字
        String idCard = cardNumber.substring(0, 17);
        String check = "^[0-9]*$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(idCard);
        result = result && matcher.matches();

        // 身份证号的第18位校验正确
        result = result
                && (calculateVerifyCode(cardNumber) == cardNumber
                .charAt(NEW_CARD_NUMBER_LENGTH - 1));
        return result;
    }

    /**
     * 校验码（第十八位数）：
     *
     * 十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0...16 ，先对前17位数字的权求和；
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * 2; 计算模 Y = mod(S, 11)< 通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9
     * 8 7 6 5 4 3 2
     *
     * @param cardNumber
     * @return
     */
    private static char calculateVerifyCode(CharSequence cardNumber){
        int sum = 0;
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++){
            char ch = cardNumber.charAt(i);
            sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
        }
        return VERIFY_CODE[sum % 11];
    }

    public static void main(String[] args) {
//        String s = "0532-87690265";
//        String s = "13012345678";
//        // 校验手机号
//        if (validPhone(s)){
//            System.out.println("============");
//        }else {
//            System.out.println("+++++++++++");
//        }

        // 校验身份证号
        String idCard = "510921199703131421";
        if (validIdCard(idCard)){
            System.out.println("============");
        }else {
            System.out.println("+++++++++++");
        }
    }
}
