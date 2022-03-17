package com.fc.demo2._jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GameOffice implements InvocationHandler {
    // 真实对象
    private final Object target;

    public GameOffice(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("login")) {
            System.out.println("请扫码认证一下");
        }

        // 执行方法
        Object invoke = method.invoke(target, args);

        if (method.getName().equals("killBoss")) {
            System.out.println("爆装备，爆金币");
        }

        return invoke;
    }
}
