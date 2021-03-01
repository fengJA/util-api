package com.fj.test.thread.threadtest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 */
public class FizzBuzz {
    private int n;
    private Lock lock = new ReentrantLock();
    // 3
    private Condition condition = lock.newCondition();
    // 5
    private Condition a = lock.newCondition();
    // 3  5
    private Condition b = lock.newCondition();
    // 数字
    private Condition c = lock.newCondition();
    private volatile int as = 1;


    public FizzBuzz(int n) {
        this.n = n;
    }

    /**
     * 被3整除
     */
    public void fizz() throws InterruptedException {
        lock.lock();
        try{
            // if的判断只是为了阻拦第一个调用的就是fizz()方法，而不是number()方法，
            // 只要开始调用number()后，都是执行的for循环里面的代码
            if (as % 3 != 0){
                condition.await();
            }
            for (; as <= n;) {
                System.out.println("fizz");
                System.out.println("as 是" + as);
                c.signal();
                condition.await();
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * 被5整除
     */
    public void buzz() throws InterruptedException {
        lock.lock();
        try{
            // if的判断只是为了阻拦第一个调用的就是buzz()方法，而不是number()方法，
            // 只要开始调用number()后，都是执行的for循环里面的代码
            if (as % 5 != 0){
                a.await();
            }
            for (; as <= n;) {
                System.out.println("buzz");
                System.out.println("as 是" + as);
                c.signal();
                a.await();
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * 被3、5整除
     */
    public void fizzbuzz() throws InterruptedException {
        lock.lock();
        try{
            // if的判断只是为了阻拦第一个调用的就是fizzbuzz()方法，而不是number()方法，
            // 只要开始调用number()后，都是执行的for循环里面的代码
            if (!(as % 5 == 0 && as % 3 == 0)){
                b.await();
            }
            for (; as <= n;) {
                System.out.println("fizzbuzz");
                System.out.println("as 是" + as);
                c.signal();
                b.await();
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * 输出其余数字
     */
    public void number() throws InterruptedException {
        lock.lock();
        try{
            for (; as <= n; as++) {
                System.out.println("大括号 " + as);
                if (as % 5 != 0 && as % 3 != 0){
                    System.out.println(as);
                    System.out.println("as 是" + as);
                }else if (as % 5 == 0 && as % 3 == 0){
                    b.signal();
                    c.await();
                }else if (as % 5 != 0 && as % 3 == 0){
                    condition.signal();
                    c.await();
                }else{
                    a.signal();
                    c.await();
                }
            }
            // 用来唤醒所有线程，避免超时（线程不结束）
            // 比如 运行完15后， 16会被最后一个线程处理(由于判断num < n是在wait之前，所以相当于没用),
            // 所以所有线程一直运行这，一个解决办法是在线程内再检测一次是否越界
            condition.signal();
            a.signal();
            b.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int num = 15;
        FizzBuzz odd = new FizzBuzz(num);
        Thread one = new Thread(() -> {
            try {
                odd.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                odd.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread three = new Thread(() -> {
            try {
                odd.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread four = new Thread(() -> {
            try {
                odd.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        four.start();
        three.start();
        two.start();
    }
}
