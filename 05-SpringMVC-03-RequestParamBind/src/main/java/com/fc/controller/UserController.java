package com.fc.controller;

import com.fc.entity.Clazz;
import com.fc.entity.User;
import com.fc.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("testParam")
    public String testParam(String name, Integer age, String gender, String birthday, String info) {
        System.out.println(name + ":" + age + ":" + gender + ":" + birthday + ":" + info);

        return "/success.jsp";
    }

    @RequestMapping("testObject")
    public String testObject(User user) {
        System.out.println(user);

        return "/success.jsp";
    }

    @RequestMapping("testNestedObject")
    public String testNestedObject(UserInfo userInfo) {
        System.out.println(userInfo);

        return "/success.jsp";
    }

    @RequestMapping("testListAndMap")
    public String testListAndMap(Clazz clazz) {
        System.out.println(clazz);

        return "/success.jsp";
    }

    @RequestMapping("testServlet")
    public void testServlet(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");

        request.getSession(true).setAttribute("username", username);

        try {
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
