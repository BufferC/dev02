package com.fc.demo1._jdk;

// 原告，被代理对象，真实对象
public class Parties implements Lawsuit {
    @Override
    public void submit() {
        System.out.println("江南皮革厂倒闭了，老板带着小姨子跑路了...");
    }

    @Override
    public void defend() {
        System.out.println("还我血汗钱");
    }
}
