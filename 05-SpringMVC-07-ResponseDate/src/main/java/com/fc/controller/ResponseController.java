package com.fc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("response")
public class ResponseController {
    @RequestMapping("testVoid")
    public void testVoid() {
        System.out.println("测试返回值是void，无法被视图解析了");
    }

    @RequestMapping("testString")
    public String testString() {
        System.out.println("测试返回值是String，直接进行跳转");
        return "/success.jsp";
    }

    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView();

        // 设置模型
        mv.addObject("username", "易烊千玺");
        // request.setAttribute("username", "易烊千玺");

        // 设置视图
        mv.setViewName("/success.jsp");
        // request.getRequestDispatcher("/success.jsp").forward(req, resp);

        return mv;
    }

    @RequestMapping("testModelAndView2")
    // 从容器中获取ModelAndView
    public ModelAndView testModelAndView2(ModelAndView mv) {

        // 设置模型
        mv.addObject("username", "迪丽热巴");
        // request.setAttribute("username", "易烊千玺");

        // 设置视图
        mv.setViewName("/success.jsp");
        // request.getRequestDispatcher("/success.jsp").forward(req, resp);

        return mv;
    }

    @RequestMapping("testModelAndView3")
    // 从容器中获取ModelAndView
    public String testModelAndView3(Model model) {
        model.addAttribute("username", "玛卡巴卡");

        return "/success.jsp";
    }

    @RequestMapping("testVoidRedirect")
    public void testVoidRedirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/success.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("testVoidForward")
    public void testVoidForward(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("username", "玛卡巴卡");

        try {
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("testStringRedirect")
    public String testStringRedirect() {
        return "redirect:/success.jsp";
    }

    @RequestMapping("testStringForward")
    public String testStringForward(Model model) {
        model.addAttribute("username", "唔西迪西");
        return "forward:/success.jsp";
    }

    // ResponseBody注解会自动跳过视图解析器
    // 并且返回json类型的数据
    @RequestMapping("testResponseBody")
    @ResponseBody
    public void testResponseBody() {
        System.out.println("测试@ResponseBody注解");
    }

    @RequestMapping("testResponseBodyString")
    @ResponseBody
    public String testResponseBodyString() {
        // 如果直接使用字符串返回就相当于调用
        // resp.getWriter().write("/success.jsp");
        return "/success.jsp";
    }

    @RequestMapping(value = "testHtmlString", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String testHtmlString() {
        return "<h1 align='center' style='color: green'>易烊千玺</h1>";
    }

    @RequestMapping(value = "testJsonString", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String testJsonString() {
        return "{\"username\": \"易烊千玺\", \"password\": \"123456\"}";
    }

    @RequestMapping(value = "testJsonObject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String testJsonObject() {
        User user = new User();
        user.setName("易烊千玺");
        user.setAge(22);
        user.setGender("男");
        user.setBirthday(new Date());
        user.setInfo("四个字");

        // 获取对象映射器
        ObjectMapper objectMapper = new ObjectMapper();

        String json = null;
        try {
            // 将对象转为对应的json字符串
            json = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(json);

        return json;
    }

    @RequestMapping(value = "testObject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User testObject() {
        User user = new User();
        user.setName("易烊千玺");
        user.setAge(22);
        user.setGender("男");
        user.setBirthday(new Date());
        user.setInfo("四个字");

        return user;
    }

    @RequestMapping(value = "testMap", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> testMap() {
        User user = new User();
        user.setName("易烊千玺");
        user.setAge(22);
        user.setGender("男");
        user.setBirthday(new Date());
        user.setInfo("四个字");

        List<String> foods = new ArrayList<>();

        foods.add("烤羊排");
        foods.add("烤乳猪");
        foods.add("烤红薯");
        foods.add("烤玉米");

        Map<String, Object> map = new HashMap<>();

        map.put("code", 200);
        map.put("success", true);
        map.put("message", "成功");
        map.put("data", user);
        map.put("foods", foods);

        return map;
    }
}
