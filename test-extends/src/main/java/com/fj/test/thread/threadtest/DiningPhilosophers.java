package com.fj.test.thread.threadtest;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 五个人围成圈坐，没人面前一碗面，旁边一个叉子，一个人同时拿着左右两边的叉子，
 * 才可以吃饭，吃了后放下叉子，其他人再吃，知道每个人都吃了
 */
public class DiningPhilosophers {
    private Semaphore[] semaphoreArr = new Semaphore[]{
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)
    };

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftPos = philosopher;
        int rightPos = (philosopher + 4)%5;
        int radomSeq = 0;
        Semaphore semaphoreFist, semaphoreSecond;
        Runnable fistPic, secondPic, fistPut, secondPut;
        while(true){
            // 随机方向开始
            radomSeq = new Random().nextInt(2);
            if(0 == radomSeq){
                semaphoreFist = semaphoreArr[leftPos];
                semaphoreSecond = semaphoreArr[rightPos];
                fistPic = pickLeftFork;
                secondPic = pickRightFork;
                fistPut = putLeftFork;
                secondPut = putRightFork;
            } else{
                semaphoreFist = semaphoreArr[rightPos];
                semaphoreSecond = semaphoreArr[leftPos];
                fistPic = pickRightFork;
                secondPic = pickLeftFork;
                fistPut = putRightFork;
                secondPut = putLeftFork;
            }

            if(pickBysequence(semaphoreFist, semaphoreSecond, fistPic, secondPic, eat, fistPut, secondPut))
                return;
        }
    }

    private boolean pickBysequence(Semaphore semaphoreFist,
                                   Semaphore semaphoreSecond,
                                   Runnable fistPic,
                                   Runnable secondPic,
                                   Runnable eat,
                                   Runnable fistPut,
                                   Runnable secondPut) throws InterruptedException{

        boolean permit = false, permitSec = false;
        try {
            // 获取第一根
            permit = semaphoreFist.tryAcquire(5, TimeUnit.MILLISECONDS);
            if (permit) {
                // 拿第一根叉子
                fistPic.run();
                // 获取第二根叉子
                permitSec = semaphoreSecond.tryAcquire(5, TimeUnit.MILLISECONDS);
                if (permitSec) {
                    // 获取成功
                    secondPic.run();
                    eat.run();
                    fistPut.run();
                    secondPut.run();
                    return true;
                } else {
                    // 拿不到第二根，放下第一根
                    fistPut.run();
                    return false;
                }
            } else {
                return false;
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            if (permit) {
                semaphoreFist.release();
            }
            if (permitSec) {
                semaphoreSecond.release();
            }
        }

    }
}
