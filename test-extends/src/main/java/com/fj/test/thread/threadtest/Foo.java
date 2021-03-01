package com.fj.test.thread.threadtest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 三个线程，共享一个实例，无论线程先后，只能依次输出one tow three
 */
public class Foo {
//    private Foo() {
//
//    }

    /**
     * 方法一
     */
    private Semaphore a = new Semaphore(0);
    private Semaphore b = new Semaphore(0);

    public void first() throws InterruptedException {
        System.out.println("one");
        a.release();// 表示线程使用完共享资源后，释放，并加一；此时才能调用second()
    }

    public void second() throws InterruptedException {
        a.acquire();// 调用acquire()后会减一，发现是0，就堵塞了，只有调用first()方法
        System.out.println("Two");
        b.release();// 表示线程使用完共享资源后，释放，并加一；third()
    }

    public void third() throws InterruptedException {
        b.acquire();
        System.out.println("three");
    }

    /**
     * 方法二
     */
    private CountDownLatch c = new CountDownLatch(1);
    private CountDownLatch d = new CountDownLatch(1);

    public void first1() throws InterruptedException {
        System.out.println("one");
        c.countDown(); // c的count会减一
    }

    public void second1() throws InterruptedException {
        c.await(); // 会等待，直到count为0
        System.out.println("Two");
        d.countDown();
    }

    public void third1() throws InterruptedException {
        d.await();
        System.out.println("three");
    }

    /**
     * 方法三：采用阻塞队列
     */
    private Foo() {
        this.one = new LinkedBlockingQueue<>();
        this.two = new LinkedBlockingQueue<>();
    }

    private BlockingQueue<Integer> one;
    private BlockingQueue<Integer> two;

    public void first2() throws InterruptedException {
        System.out.println("one");
        this.one.put(1); // 如果队列满了，一直阻塞，直到队列不满了或者线程被中断-->阻塞
    }

    public void second2() throws InterruptedException {
        this.one.take(); // 如果队列空了，一直阻塞，直到队列不为空或者线程被中断-->阻塞
        System.out.println("two");
        this.two.add(1);
    }

    public void third2() throws InterruptedException {
        this.two.take();
        System.out.println("three");
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> {
            try {
//                foo.first();
                foo.first2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
//                foo.second();
                foo.second2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
//                foo.third();
                foo.third2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t3.start();
        t1.start();
    }
}
