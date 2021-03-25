package com.fj.test.likou.recursion;

import java.lang.reflect.Field;

public class Testhuds {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        Score score = new Score();
        Field high = score.getClass().getDeclaredField("str");
        high.setAccessible(true);
        high.set(score, "feng");

        System.out.println(high.get(score));
        Class<?> aClass = Class.forName("com.fj.test.likou.recursion.Score");
        Field high2 = aClass.getDeclaredField("high");
        high2.setAccessible(true);
        high2.set(score, 1.00);
        // 通过反射创建实例
        Score o = (Score)aClass.newInstance();
        Field high3 = o.getClass().getDeclaredField("str");
        high3.setAccessible(true);
        high3.set(score, "feng123");
        System.out.println(high3.get(score));

        aClass.getDeclaredConstructor(String.class);

    }

    private void thread(){

        Thread thread = new Thread(() -> {

        }, "A");

    }
}
