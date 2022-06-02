package com.fc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.annotation.Autowired;
import com.fc.annotation.Controller;
import com.fc.annotation.RequestMapping;
import com.fc.annotation.RequestParam;
import com.fc.entity.User;
import com.fc.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public void findAll(HttpServletResponse response) throws JsonProcessingException {
        List<User> users = userService.findAll();

        // 获取jackson对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 对象转json字符串
        String json = objectMapper.writeValueAsString(users);

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter printWriter;

        try {
            printWriter = response.getWriter();

            printWriter.print(json);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("发生了IO异常");
        }
    }

    @RequestMapping("findById")
    public void findById(HttpServletResponse response, @RequestParam("id") Integer id) throws JsonProcessingException {
        User user = userService.findById(id);

        // 获取jackson对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 对象转json字符串
        String json = objectMapper.writeValueAsString(user);

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter printWriter;

        try {
            printWriter = response.getWriter();

            printWriter.print(json);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("发生了IO异常");
        }
    }

    @RequestMapping("login")
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      HttpServletResponse response) throws JsonProcessingException {
        User user = userService.login(username, password);

        // 获取jackson对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 对象转json字符串
        String json = objectMapper.writeValueAsString(user);

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter printWriter;

        try {
            printWriter = response.getWriter();

            printWriter.print(json);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("发生了IO异常");
        }
    }
}
