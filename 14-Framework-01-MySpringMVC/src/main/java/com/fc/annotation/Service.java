package com.fc.annotation;

import java.lang.annotation.*;

// 自定义Service注解
@Target(ElementType.TYPE) // 类上有效
@Retention(RetentionPolicy.RUNTIME) // 项目运行期间都可以使用
@Documented // javadoc文档
public @interface Service {
    // 携带了一个参数，名字叫value，默认值是空字符串
    String value() default "";
}
