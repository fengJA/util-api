package com.fj.test.hei;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            System.out.println("t1=======开始了");
            for (int j = 0; j < 5000; j++) {
                room.increment();
            }
            System.out.println("t1=======结束了");
        }, "t1");
        Thread t2 = new Thread(() -> {
            System.out.println("t2==============开始了");
            for (int j = 0; j < 5000; j++) {
                room.decrement();
            }
            System.out.println("t2============开始了");
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("结果为：" + room.get());
        new ArrayList<Integer>().add(1);
    }
}

class Room {
    int value = 0;
    public void increment() {
        synchronized (this) {
            value++;
        }
    }
    public void decrement() {
        synchronized (this) {
            value--;
        }
    }
    public int get() {
        synchronized (this) {
            return value;
        }
    }
}
