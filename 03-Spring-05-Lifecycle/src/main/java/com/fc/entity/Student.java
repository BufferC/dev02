package com.fc.entity;

public class Student {
    private Integer id;
    private String name;
    private Byte age;

    public Student() {
        System.out.println("无参构造被执行~~~");
    }

    public void init() {
        System.out.println("初始化方法被执行~~~");
    }

    public void destroy() {
        System.out.println("销毁方法被执行~~~");
    }
}
