package com.fj.test.thread;

public class ThreadOrder {

    static int x;
    static String s;
    public static void main(String[] args) {
//        new Thread(new ThreadA()).start();
        System.out.println("X = " + x);// 当为类的字段时，会为其赋初值，输出 X= 0
        int y;
//        System.out.println("Y = " + y );// 当为局部变量时，不会自动赋初始值，编译时就会报错
        System.out.println("S = "+ s); // 输出S= null
        String st;
//        System.out.println("st = " + st); // // 当为局部变量时，不会自动赋初始值，编译时就会报错
        char c = 65535; // 占用两个字节，所以最大值为65536
    }

    public static void threadA(){
        System.out.println(Thread.currentThread().getName() + "A");
    }

    public static void threadB(){
        System.out.println(Thread.currentThread().getName() + "B");
    }

    public static void threadC(){
        System.out.println(Thread.currentThread().getName() + "C");
    }


}


