package com.fj.test.thread.threadtest;

import java.util.concurrent.Semaphore;

public class H2O {
    public H2O() {

    }

    private Semaphore a = new Semaphore(2);
    private Semaphore b = new Semaphore(0);

    public void hydrogen() throws InterruptedException {
        a.acquire(1);
        System.out.println("H");
        b.release(1);
    }

    public void oxygen() throws InterruptedException {
        System.out.println("=======");
        b.acquire(2);
        System.out.println("O");
        a.release(2);
    }

    public static void main(String[] args) throws InterruptedException {
        H2O h2O = new H2O();
        Thread one = new Thread(() -> {
            try {
                h2O.hydrogen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                h2O.oxygen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread three = new Thread(() -> {
            try {
                h2O.hydrogen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        two.start();
        Thread.sleep(3);
        three.start();
        one.start();
    }
}
