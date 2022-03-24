package com.fc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("annotation")
public class AnnotationController {
    @RequestMapping("testRequestParam")
    public String testRequestParam(@RequestParam(value = "userId", required = true, defaultValue = "1") String id) {
        System.out.println("获取到的参数：" + id);
        return "/success.jsp";
    }

    @RequestMapping("page")
    public String page(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        System.out.println("当前页：" + pageNo + "，每页显示多少条数据：" + pageSize);

        return "/success.jsp";
    }

    @RequestMapping("keyword")
    public String keyword(@RequestParam(value = "name", defaultValue = "") String name,
                       @RequestParam(value = "gender", defaultValue = "") String gender) {
        System.out.println("是否有姓名：" + name + "，是否有年龄：" + gender);

        return "/success.jsp";
    }
}
