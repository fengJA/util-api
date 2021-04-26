package com.fj.test.thread.extendstest;

import java.util.Random;

public class ExtendA {
    public ExtendA(){
        System.out.println("ExtendA....");
    }

    public ExtendA(int i) {
        System.out.println("ExtendA....." + i);
    }

    public void scal(int i){
//        s.scal(i);
        System.out.println("scal......" + i);
    }

    public void scal(){
//        s.scal(i);
        System.out.println("scal......");
    }

    public void dup(){
        System.out.println("dup......");
    }

    public void respect(){
        System.out.println("A respect...");
    }

//    private ExtendA s = new ExtendA();

    private static final String str = "fin";
    private static Random random = new Random(40);
    private final int i4 = random.nextInt(20);

    public static void main1(String[] args) {
//        ExtendA extendA = new ExtendA();
//        extendA.scal(100);

//        try{
//            System.out.println("I am try ...");
////            int i = 1 / 0;
//            return;
//        }catch (Exception e){
//            System.out.println("I am exception.....");
//        }finally {
//            System.out.println("I am fianlly...");
//        }

        final int i = 21;
        System.out.println(i);
        final long workerIdBits = 5L;
        long aa = (-1L << workerIdBits);
        System.out.println(aa);
        System.out.println(Long.toBinaryString(aa));
        final long maxWorkerId = -1L ^ aa;
        System.out.println(maxWorkerId);
        System.out.println(Long.toBinaryString(maxWorkerId));
    }

    private final void q(int a){

    }

    private final void q(int a, int b){

    }

    class oners{
        private static final int num = 8;
        String s1;
        private void onerM(){
            System.out.println("onerM.....");
        }
        public void pubM(){
            System.out.println("pubM.....");
        }
    }
}
