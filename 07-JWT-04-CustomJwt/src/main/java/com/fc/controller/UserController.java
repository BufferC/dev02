package com.fc.controller;

import com.fc.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @RequestMapping("login")
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<>();

        if (username.equals("易烊千玺") && password.equals("123456")) {
            result.put("success", true);
            result.put("auth", "admin");
        } else {
            result.put("success", true);
            result.put("auth", "user");
        }

        result.put("token", JwtUtil.getToken(result, "zhen xiang"));

        return result;
    }

    @RequestMapping("show")
    public Map<String, Object> show(@RequestParam String token) {
        // 验证token
        Map<String, Object> map = JwtUtil.verify(token, "zhen xiang");

        Boolean success = (Boolean) map.get("success");

        if (success) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");

            // 获取权限
            String auth = String.valueOf(data.get("auth"));

            if (auth.equals("user") || auth.equals("admin")) {
                map.put("message", "请查阅学生成绩");
            } else {
                map.put("message", "权限不够");
            }

        } else {
            map.put("message", "token验证失败");
        }

        return map;
    }

    @RequestMapping("modify")
    public Map<String, Object> modify(@RequestParam String token) {
        // 验证token
        Map<String, Object> map = JwtUtil.verify(token, "zhen xiang");

        Boolean success = (Boolean) map.get("success");

        if (success) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            // 获取权限
            String auth = String.valueOf(data.get("auth"));

            if (auth.equals("admin")) {
                map.put("message", "可以加分");
            } else {
                map.put("message", "权限不够");
            }

        } else {
            map.put("message", "token验证失败");
        }

        return map;
    }
}
