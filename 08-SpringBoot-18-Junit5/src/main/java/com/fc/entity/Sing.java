package com.fc.entity;

// 函数式接口
// 被这个注解声明的接口中只能有一个抽象方法
@FunctionalInterface
public interface Sing {
    void rap(String name, Integer time);
}
