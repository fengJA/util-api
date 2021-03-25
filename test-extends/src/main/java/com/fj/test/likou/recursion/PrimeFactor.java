package com.fj.test.likou.recursion;

/**
 * 正数质因数分解：输入90,打印出90=2*3*3*5
 */
public class PrimeFactor {
    private static int key = 2;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        System.out.println(prime(862631).substring(0, sb.length() - 1));
    }

    private static StringBuilder prime(int num){
        if (key > num){
            return sb;
        }
        int i = num % key;
        if (i == 0){
            sb.append(key).append("*");
            prime(num / key);
        }else {
            key ++;
            prime(num);
        }
        return sb;
    }
}
