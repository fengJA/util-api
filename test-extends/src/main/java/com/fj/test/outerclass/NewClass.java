package com.fj.test.outerclass;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.springframework.util.StringUtils;
import sun.misc.Unsafe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

abstract class NewClass {
    abstract String value();

//    public static void main(String[] args) {
//        BigDecimal bigDecimal = new BigDecimal("2.00");
//        System.out.println(bigDecimal);
//
////        int s = (int) bigDecimal;
////        float s = (Float) bigDecimal;
//        Unsafe unsafe = Unsafe.getUnsafe();
//
//    }

    public static void main(String[] args) {
//        ise("我是仙女");
        String tl = "14757000";
//        mileageCensusDto.setDuration(String.valueOf(new BigDecimal(mileageCensusDto.getDuration()).divide(new BigDecimal(1000 * 60 * 60))));
        BigDecimal divide = new BigDecimal(tl).divide(new BigDecimal(3600000),2,BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
        String s = String.valueOf(divide);
        System.out.println(s);
        as();
    }

    private static boolean ise(String str){
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                System.out.println("我错了");
                return false;
            }
        }
        return true;
    }

    private static void as(){
        String s = "      ";
        if (StringUtils.isEmpty(s)){
            System.out.println("我是空的");
        }else {
            System.out.println("hhh");
        }
    }

    
}
