package com.fj.test.likou.recursion;

/**
 * 100到200之间的素数
 */
public class PrimeNumber {
    public static void main(String[] args) {
//        int digui = add(5);
//        System.out.println(digui);
//        int num = 5;
//        int add = 0;
//        for (int j = num; j > 0; j--) {
//            System.out.println("=== knm = " +knm(j));
//            add = add + knm(j);
//        }
//        System.out.println(add);
        kpa(200);// 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199
        for (int j = 100; j < 200; j++) {
        }
    }

    private static int add(int num){
        if (num <= 0){
            return num;
        }
        return num + add(num - 1);
    }
    private static int i = 0;
    private static int primeNums = 0;

    /**
     * 1 1 2 3 5 8
     */
    private static int knm(int num){
        if (num <= 2){
            i = (++i);
            return 1;
        }
        return knm(num - 1) + knm(num - 2);
    }

    private static int kpa(int num){
        if (num <= 100){
            return num;
        }
        int middle = num / 2 +1;
        int j;
        int k = 0;
        for (j = 2; j <= middle; j++) {
            if (num % j == 0){
                ++k;
                break;
            }
        }
        if ( k == 0){
            System.out.print(num + " ");
        }
        return kpa(num -1);
    }
}
