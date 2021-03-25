package com.fj.cycle.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {

    BeProxyTest beProxyTest = null;

    public JDKProxy(BeProxyTest beProxyTest) {
        this.beProxyTest = beProxyTest;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK 开始了");
        method.invoke(beProxyTest);
        System.out.println("JDK 结束了");
        return null;
    }
}
