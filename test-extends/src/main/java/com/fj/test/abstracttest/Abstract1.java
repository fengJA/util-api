package com.fj.test.abstracttest;

import com.fj.test.interfacetest.Ainf;


public abstract class Abstract1 extends Abstract2 implements Ainf {
    private int i;
    // 初始定义时就必须赋值
    public static final String s = "sssss";
    abstract void a1();

    void a2(){
        System.out.println("oooooo");
    }

    static {
        // 初始定义是不用赋值，但是使用时必须赋值
        final int b;
    }

    class oner{

    }
}
