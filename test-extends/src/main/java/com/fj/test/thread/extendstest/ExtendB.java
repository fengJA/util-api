package com.fj.test.thread.extendstest;

public class ExtendB extends ExtendA {
    public ExtendB() {
//        super(11);
        System.out.println("ExtendB......");
    }

    public void scal(){
//        super.scal(100);
        System.out.println("scal2......");
    }

    public void oh(){
        super.scal(100);
        System.out.println("scal2......");
    }

    public static void main(String[] args) {
//        ExtendB extendB = new ExtendB();
//        extendB.scal();
//        extendB.dup();

        ExtendA extendA = new ExtendB();
//        ((ExtendB) extendA).oh();
        extendA.scal();
    }
}
