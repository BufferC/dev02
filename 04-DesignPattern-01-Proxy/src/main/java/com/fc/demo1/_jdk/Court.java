package com.fc.demo1._jdk;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Court {
    @Test
    public void test() {
        // 原告
        Lawsuit parties = new Parties();

        // 获取律师事务所
        InvocationHandler lawOffice = new LawOffice(parties);

        // 获取代理
        Lawsuit proxy = (Lawsuit) Proxy.newProxyInstance(parties.getClass().getClassLoader(),
                parties.getClass().getInterfaces(), lawOffice);

        proxy.submit();
        proxy.defend();
    }
}
