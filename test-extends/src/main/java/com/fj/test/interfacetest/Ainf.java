package com.fj.test.interfacetest;

import com.fj.test.abstracttest.Abstract2;

public interface Ainf extends Int2, Int3{
    void ainf();

    static void as(){
        System.out.println("静态方法开始了。。。。");
        int a;
        System.out.println(a = 0);
        int num = oner.num;
        System.out.println(num);
        oner oner = new oner();
        String s = oner.s1;
        System.out.println(s);
        oner.onerM();

    }

    default void sa(){
        System.out.println("io");
    }
    public static final int s = 0;

    /**
     * 在外部类中的访问方法：
     * Ainf.oner oner = new Ainf.oner();
     * oner.pubM();
     */
    class oner{
        private static final int num = 8;
        String s1;
        private void onerM(){
            System.out.println("onerM.....");
        }
        static {
            System.out.println("static.....");
        }

        public void pubM(){
            System.out.println("pubM.....");
        }
    }


}
