package com.fj.test.interfacetest.Imp;

import com.fj.test.interfacetest.Ainf;

public class BImp implements Ainf {
    @Override
    public void ainf() {
        System.out.println("Bimp");
    }

    @Override
    public void oM() {
        System.out.println("Int2接口的方法");
    }

    public static void main(String[] args) {
        Ainf bImp = new BImp();
        bImp.ainf();
        bImp.sa(); // 调用接口中的默认方法
        oner oner = new oner();// 调用接口中的内部类
        Ainf.as(); // 调用接口中的静态方法
    }
}
