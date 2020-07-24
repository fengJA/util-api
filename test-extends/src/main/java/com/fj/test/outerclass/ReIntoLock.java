package com.fj.test.outerclass;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReIntoLock {
    public synchronized void lo(){
        System.out.println("1111");
        ol();
    }

    public synchronized void ol(){
        System.out.println("2222");
    }

    private Lock lock = new ReentrantLock();

    public void fj(){
        lock.lock();
        System.out.println("9999");
        jf();
        lock.unlock();
    }

    public void jf(){
        lock.lock();
        System.out.println("000");
        lock.unlock();
    }
}
