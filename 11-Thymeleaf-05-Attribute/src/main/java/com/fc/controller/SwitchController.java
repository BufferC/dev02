package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwitchController {
    @RequestMapping("switch")
    public String test(Model model) {
        model.addAttribute("score", 100);
        model.addAttribute("gender", true);
        model.addAttribute("food", "开水白菜");

        switch ("鱼香肉丝") {
            case "宫保鸡丁":
                System.out.println("宫保鸡丁");
                break;
            case "麻婆豆腐":
                System.out.println("麻婆豆腐");
                break;
            case "鱼香肉丝":
                System.out.println("鱼香肉丝");
                break;
            case "葱爆羊肉":
                System.out.println("葱爆羊肉");
                break;
            case "红绕肉":
                System.out.println("红绕肉");
                break;
            default:
                System.out.println("家常菜");
        }

        return "switch";
    }
}
