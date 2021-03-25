package com.fj.cycle.proxy;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始CGLib动态代理");
        Object object=methodProxy.invokeSuper(o, objects);
        System.out.println("结束CGLib动态代理");
        return object;
    }
}
