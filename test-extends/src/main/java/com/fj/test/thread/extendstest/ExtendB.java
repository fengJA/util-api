package com.fj.test.thread.extendstest;

import com.fj.test.abstracttest.Abstract2;
import com.fj.test.interfacetest.Ainf;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public final class ExtendB extends ExtendA{

    static ExtendB s1 = new ExtendB();
    int bb;

    public static void main(String[] args) {
        ExtendA extendA = new ExtendA();
        ExtendA.oners oners = extendA.new oners();
        ExtendA.oners oners1 = new ExtendA().new oners();
    }










    public ExtendB() {
//        super(11);
        System.out.println("ExtendB......");
    }

    public void scal(){
//        super.scal(100);
        System.out.println("scal2......");
    }

    public void scal(int a){
//        super.scal(100);
        System.out.println("scal ExtendB......");
    }

    public void oh(){
        super.scal(100);
        System.out.println("scal2......");
    }

    public void respect( ){
        System.out.println("B respect...");
    }

    public void bb(){
        System.out.println("BB B");
    }

    private List s;
    public static void main1(String[] args) {
        ExtendB extendB = new ExtendB();
        System.out.println(extendB.s);
        List list;
//        extendB.scal();
//        extendB.dup();

        ExtendA extendA = new ExtendB();
//        ((ExtendB) extendA).oh();
        extendA.scal();

    }
}
