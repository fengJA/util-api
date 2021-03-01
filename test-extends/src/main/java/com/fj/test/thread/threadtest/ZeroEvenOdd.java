package com.fj.test.thread.threadtest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程共享一个实例，打印0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0 9
 */
public class ZeroEvenOdd {
    private int n;
    private Semaphore a = new Semaphore(1);
    private Semaphore b = new Semaphore(0);
    private Semaphore c = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(Integer printNumber) throws InterruptedException {
        for (int i = 0; i < printNumber - 1; i++) {
            a.acquire();
            System.out.print(0 + " ");
            if(i % 2 == 0){
                b.release();
            } else{
                c.release();
            }
        }
    }

    public void even(Integer printNumber) throws InterruptedException {
        for (int i = 1; i < printNumber; i += 2) {
            b.acquire();
            System.out.print(i + " ");
            a.release();
        }
    }

    public void odd(Integer printNumber) throws InterruptedException {
        for (int i = 2; i < printNumber; i += 2) {
            c.acquire();
            System.out.print(i + " ");
            a.release();
        }
    }

    /**
     * 方法二
     */
    private final Lock lock = new ReentrantLock();
    private final Condition d = lock.newCondition();
    private final Condition e = lock.newCondition();
    private final Condition f = lock.newCondition();
    private int state = 0;

    public void zero1(Integer printNumber) throws InterruptedException {
        for (int i = 0; i < printNumber - 1; i++) {
            lock.lock();
            if (state != 0){
                d.await();
            }
            System.out.print(0 + " ");
            if(i % 2 == 0){
                state = 1;
                e.signal();
            } else{
                state = 2;
                f.signal();
            }
            lock.unlock();
        }
    }

    public void even1(Integer printNumber) throws InterruptedException {
        for (int i = 1; i < printNumber; i += 2) {
            lock.lock();
            if (state != 1){
                e.await();
            }
            System.out.print(i + " ");
            state = 0;
            d.signal();
            lock.unlock();
        }
    }

    public void odd1(Integer printNumber) throws InterruptedException {
        for (int i = 2; i < printNumber; i += 2) {
            lock.lock();
            if (state != 2){
                f.await();
            }
            System.out.print(i + " ");
            state = 0;
            d.signal();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int num = 20;
        ZeroEvenOdd odd = new ZeroEvenOdd(num);
        Thread one = new Thread(() -> {
            try {
                odd.zero1(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                odd.even1(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread three = new Thread(() -> {
            try {
                odd.odd1(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        three.start();
        one.start();
        two.start();
    }
}
