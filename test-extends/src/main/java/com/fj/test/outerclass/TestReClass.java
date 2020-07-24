package com.fj.test.outerclass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReClass {

    public static void main(String[] args) {
        ReIntoLock reIntoLock = new ReIntoLock();
//        reIntoLock.lo();
//        reIntoLock.fj();

        String as = "fengjia";
        String sa = "fengjia";
        if (as == sa){
            System.out.println("1111");
        }else {
            System.out.println("222");
        }
    }

}
