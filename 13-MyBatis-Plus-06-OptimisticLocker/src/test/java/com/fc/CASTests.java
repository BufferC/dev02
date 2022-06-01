package com.fc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

// 模拟一个买东西的场景
public class CASTests {
    // 准备一万颗糖
    // 并发原子类来解决这个问题
    //int sugar = 10000;
    AtomicInteger sugar = new AtomicInteger(10000);

    public void buySugar() {
        //sugar--;
        // 这个方法就是--操作
        sugar.decrementAndGet();
    }

    // 卖家卖糖
    @Test
    void testProducer() throws InterruptedException {
        // 卖了10次
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 一次卖1000颗
                for (int j = 0; j < 1000; j++) {
                    buySugar();
                }
            }).start();
        }

        // 卖得太快，休息一下
        Thread.sleep(3000);

        System.out.println("当前还有剩余的糖：" + sugar);
    }
}
