package com.fc.advice;

import com.fc.exception.SingletonDogException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

// 携带了这个@Component注解
@ControllerAdvice
public class ExceptionAdvice {
    // 只要发生了这个异常，就会执行此方法
    @ExceptionHandler(SingletonDogException.class)
    @ResponseBody
    public Map<String, Object> handleSingletonDogException(Exception exception) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", "-1");
        map.put("message", "路见不平一声吼，你还没有男朋友");
        map.put("success", false);
        map.put("data", exception.getMessage());

        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception exception) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", "-1");
        map.put("message", exception.getMessage());
        map.put("success", false);
        map.put("data", exception.getMessage());

        return map;
    }
}
