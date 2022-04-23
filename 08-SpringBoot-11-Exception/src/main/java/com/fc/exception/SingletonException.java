package com.fc.exception;

// 自定义单身狗异常
public class SingletonException extends Exception {
    public SingletonException() {
        super();
    }

    public SingletonException(String message) {
        super(message);
    }
}
