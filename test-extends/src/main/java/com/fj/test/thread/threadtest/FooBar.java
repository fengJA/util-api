package com.fj.test.thread.threadtest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程共享一个实例，打印FooBarFooBar
 */
public class FooBar {
    private int n;
    private Semaphore a = new Semaphore(0);
    private Semaphore b = new Semaphore(1);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            b.acquire();
            System.out.print("Foo");
            a.release();
        }
    }

    public void bar() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            a.acquire();
            System.out.print("Bar");
            b.release();
        }
    }

    /**
     * 方法二
     */
    private volatile boolean flag = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void foo1() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            if (flag){
                // 当调用await()方法后，当前线程会释放锁并在此等待，而其他线程调用Condition对象的signal()方法，
                // 通知当前线程后，当前线程才从await()方法返回，并且在返回前已经获取了锁。
                condition.await();
            }
            System.out.print("Foo");
            flag = true;
            condition.signalAll();
            lock.unlock();
        }
    }

    public void bar1() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            if (!flag)
                condition.await();
            System.out.print("Bar");
            flag = false;
            condition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        Thread one = new Thread(() -> {
            try {
                fooBar.foo1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                fooBar.bar1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        two.start();
        one.start();
    }
}
