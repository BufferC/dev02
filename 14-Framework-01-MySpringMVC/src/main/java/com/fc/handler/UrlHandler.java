package com.fc.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

// 自定义的映射处理器
// 包含了需要被执行的方法以及所在的类的对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlHandler {
    private Method method; // 要被执行的方法
    private Object obj; // 所在的对象
}
