package com.fj.cycle.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class TestS {
    public static void main(String[] args) {
        BeProxyTest beProxyTest = new BeProxyTest();
        JDKProxy jdkProxy = new JDKProxy(beProxyTest);
        IBeProxyTest iBeProxyTest = (IBeProxyTest) Proxy.newProxyInstance(
                beProxyTest.getClass().getClassLoader(), beProxyTest.getClass().getInterfaces(), jdkProxy);
        iBeProxyTest.sout();// 这一步才会开始输出
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(BeProxyTest.class);
//        enhancer.setCallback(new CglibProxy());
//        BeProxyTest beProxyTest = (BeProxyTest) enhancer.create();
//        beProxyTest.sout();// 这一步才会开始打印输出
    }
}
