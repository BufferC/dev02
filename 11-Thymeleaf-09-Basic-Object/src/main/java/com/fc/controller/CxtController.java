package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CxtController {
    @RequestMapping("cxt")
    public String test(Model model, HttpServletRequest request) {
        // model对应的就是request，但是比request优先级要高
        //model.addAttribute("attr", "model");
        request.setAttribute("attr", "request");
        request.getSession().setAttribute("attr", "session");
        request.getServletContext().setAttribute("attr", "servletContext");

        return "cxt";
    }
}
