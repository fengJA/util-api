package com.fj.test.thread;

import java.util.concurrent.Callable;

public class CallableTest implements Callable {

    @Override
    public String call() throws Exception{
        Thread.sleep(3000);
        return "fengjiajia";
    }
            }
