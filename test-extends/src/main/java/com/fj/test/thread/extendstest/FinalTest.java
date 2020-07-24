package com.fj.test.thread.extendstest;

import java.util.Vector;

public class FinalTest {
    private final int a;
    private final int j;
    public FinalTest() {
        this.a = 14;
        this.j = 7;
    }

    public FinalTest(int s){
        this.a = 25;
        this.j = 7;
    }

    int g(final int i){
//        return i++;
        return i + 1;
    }

    void f(final int s){
        System.out.println(s + 1);
//        System.out.println(s++);
    }

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        System.out.println(finalTest);
        System.out.println(finalTest.g(2));
        finalTest.f(2);
    }

    @Override
    public String toString() {
        return "FinalTest{" +
                "a=" + a +
                ", j=" + j +
                '}';
    }
}
