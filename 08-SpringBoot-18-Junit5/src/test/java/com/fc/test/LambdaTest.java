package com.fc.test;

import com.fc.entity.Sing;
import org.junit.jupiter.api.Test;

public class LambdaTest {
    void sing(Sing sing) {
        sing.rap("123", 3);
    }

    class Singer implements Sing {
        @Override
        public void rap(String name, Integer time) {
            System.out.println("唱" + name + "多长时间：" + time);
        }
    }

    @Test
    void test() {
        // 最原始的写法
        sing(new Singer());
        // 匿名内部类的形式
        sing(new Sing() {
            @Override
            public void rap(String name, Integer time) {
                System.out.println("唱" + name + "多长时间：" + time);
            }
        });
        // Lambda表达式
        sing((name, time) -> System.out.println("唱" + name + time));
    }
}