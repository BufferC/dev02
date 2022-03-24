package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
