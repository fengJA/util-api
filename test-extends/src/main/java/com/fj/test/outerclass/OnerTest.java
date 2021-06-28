package com.fj.test.outerclass;

import com.fj.test.interfacetest.Ainf;
import com.fj.test.interfacetest.Int2;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class OnerTest {
    public static void main1(String[] args) {
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

    public static void main(String[] args) {

        for (int i = 0; i < 3; ++i) {
            System.out.println(i);
            new HashMap<>(55).remove("");
            new HashMap<>(55).put("","");
            new ConcurrentHashMap<>().put("1","1");
            new ConcurrentHashMap<>(32).put("","");
            new ConcurrentHashMap<>(32).remove("");
            LongAdder longAdder = new LongAdder();
            longAdder.increment();
        }
    }
}
