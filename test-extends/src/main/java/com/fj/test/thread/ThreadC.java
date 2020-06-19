package com.fj.test.thread;

public class ThreadC implements Runnable {
    @Override
    public void run() {
        ThreadOrder.threadC();
    }
}
