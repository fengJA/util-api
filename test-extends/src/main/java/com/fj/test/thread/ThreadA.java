package com.fj.test.thread;


import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Semaphore;

public class ThreadA implements Runnable{
    @Override
    public void run() {
        new Thread(new ThreadB()).start();
        ThreadOrder.threadA();
    }

    public static void main(String[] args) {
        List<String> first = new ArrayList<>(5);
        first.add("fengjia");
//        first.add("libai");
//        first.add("dufu");
//        first.add("aoteman");
//        first.add("12");
        first.sort(Comparator.comparing(String::hashCode));
        for (String a : first){
            System.out.print(a);
            System.out.print("==");
        }
        System.out.println();
        System.out.println("++++++++++++++");
        List<String> second = new ArrayList<>(2);
        second.add("fengjia");
        second.sort(Comparator.comparing(String::hashCode));
        for (String b : second){
            System.out.print(b);
            System.out.print("==");
        }
        if (first.toString().equals(second.toString())){
            System.out.println("他们相等了");
        }
        byte[] a = new byte[1];
        BigInteger bigInteger = new BigInteger(a);
        bigInteger.flipBit(12);
        BitSet bitSet = new BitSet();
        bitSet.flip(12);

        // 只能有
        Semaphore c = new Semaphore(0);

    }


    public static ThreadA as(){
        return new ThreadA();
    }


}
