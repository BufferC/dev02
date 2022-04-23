package com.fc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// 能够对@RestController进行增强
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(SingletonException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerSingleton(Exception e) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("message", "发生了单身狗异常");
        map.put("success", false);
        map.put("data", e.getMessage());

        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handlerSystem(Exception e) {
        Map<String, Object> map = new HashMap<>();

        map.put("code", HttpStatus.BAD_REQUEST);
        map.put("message", "发生了系统异常");
        map.put("success", false);
        map.put("data", e.getMessage());

        return map;
    }
}
