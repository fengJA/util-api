package com.fj.test.outerclass;

import com.fj.test.interfacetest.Ainf;
import com.fj.test.interfacetest.Int2;

public class OnerTest {
    public static void main(String[] args) {
        OnerClassT onerClassT = new OnerClassT();
        onerClassT.me();
        new OnerClassT().int2.oM(); // 调用匿名内部类
        OnerClassT.Oner2 oner2 = new OnerClassT().new Oner2();// 成员内部类
        OnerClassT.Oner3 oner3 = new OnerClassT.Oner3(); // 静态内部类
    }

//    public static void main(String[] args) {
//        Ainf.oner oner = new Ainf.oner();
//        oner.pubM();
//
//        Ainf.as();
//    }
}
