package com.fj.test.thread;

public class ThreadA implements Runnable {
    @Override
    public void run() {
        new Thread(new ThreadB()).start();
        ThreadOrder.threadA();
    }


}
