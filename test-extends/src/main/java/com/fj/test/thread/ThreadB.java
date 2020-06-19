package com.fj.test.thread;

public class ThreadB implements Runnable {
    @Override
    public void run() {
        ThreadOrder.threadB();
        new Thread(new ThreadC()).start();
    }
}
