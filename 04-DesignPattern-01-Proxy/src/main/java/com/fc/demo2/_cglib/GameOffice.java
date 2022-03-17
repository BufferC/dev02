package com.fc.demo2._cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class GameOffice implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (method.getName().equals("login")) {
            System.out.println("验证账号");
        }

        // 执行方法
        Object result = proxy.invokeSuper(obj, args);

        return result;
    }
}
