package com.fj.test.outerclass;

import com.fj.test.interfacetest.Ainf;
import com.fj.test.interfacetest.Int2;

public class OnerClassT {
    private int n;
    private static int bs;
    public void me(){
        System.out.println("me..........");
        Onerl onerl = new Onerl();
        int o1 = onerl.o1;
        System.out.println("o1 = " + o1);
        onerl.oM();
        onerl.oS();
    }

    static {
        System.out.println("static...开始了");
    }

    /**
     * 静态成员类可以有静态方法、静态代码块、静态常量；静态成员类优于其他内部类
     * 可在其他类里面创建其实例：OnerClassT.Oner3 oner3 = new OnerClassT.Oner3();
     * 如果是private的，则只能在外围类的内部调用
     * 直接在其方法里面实例化外部类，就可以调用外部的方法、成员变量,包括私有
     */
    static class Oner3{
        private int o1;
        private static int o2;
        static {
            System.out.println("静态内部类，，，，，");
        }
        private void oM(){
            System.out.println("n1 = " + bs);
            System.out.println("o2 = " + o2);
            int bs = new OnerClassT().n;
        }
        void oS(){
            System.out.println("oS1......");
        }
    }


    /**
     *  局部成员内部类,不能有静态方法、静态代码块、静态常量；
     *  可以使用外部类中所有的成员变量和成员方法（包括private的）；
     *  私有成员内部类在其他类中不能创建该实例，但是在该外部内可以创建Onerl onerl = new OnerClassT().new Onerl();
     */
    private class Onerl{
        int n;
        private int o1;
        private void oM(){
            System.out.println("n = " + OnerClassT.this.n);// 输出外部类的n
            System.out.println("n = " + n);// 输出内部类的n
        }
        void oS(){
            System.out.println("oS......");
        }
    }

    /**
     * 成员内部类不能有静态方法、静态代码块、静态常量；
     * 可在其他类里面创建其实例：OnerClassT.Oner2 oner2 = new OnerClassT().new Oner2();
     */
    class Oner2{
        private int o1;
        private void oM(){
            System.out.println("n1 = " + n);
        }
        void oS(){
            System.out.println("oS1......");
        }
    }


    /**
     *  局部内部类:不能有静态方法、静态代码块、静态常量；他是用最少
     *  只能在该方法里面创建实例调用，必须要非常简短
     */
    private void O2(){
        String f = "fffffff";
        class Oner4{
            String g = "gggggg";
            private void oM(){
                System.out.println(g);
                System.out.println(f);
            }
        }
        Oner4 oner4 = new Oner4();
        oner4.oM();
    }

    /**
     *  匿名内部类:不能有静态方法、静态代码块、静态常量；其不能被实例化
     *  调用里面的方法：new OnerClassT().int2.oM()
     *  主要用来实现接口、扩展一个类等，必须要非常简短
     */
    Int2 int2 = new Int2(){
        int w = 12;
        @Override
        public void oM(){
            System.out.println("w = " + w);
        }
    };


    public static void main(String[] args) {
        OnerClassT onerClassT = new OnerClassT();
        onerClassT.me();
        onerClassT.O2();
        Onerl onerl = new OnerClassT().new Onerl();

        Oner2 oner2 = new OnerClassT().new Oner2();

        Int2 int2 = onerClassT.int2;
        int2.oM();
    }

}
