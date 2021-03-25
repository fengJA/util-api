package com.fj.test.likou.recursion;

/**
 * 水仙花数，三位数，每一位的立方和等于本身：153
 */
public class NarcissisticNumber {
    public static void main(String[] args) {
        nar(999);
    }

    private static int nar(int num){
        if (num < 100){
            return num;
        }
        int single = num % 10;
        int hun = num / 100;
        int ten = (num - hun * 100) / 10;
        int total = single * single * single + hun * hun * hun + ten * ten * ten;
        if (total == num){
            System.out.println(total);
        }
        return nar(num - 1);
    }
}
