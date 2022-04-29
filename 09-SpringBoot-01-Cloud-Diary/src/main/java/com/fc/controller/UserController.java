package com.fc.controller;

import com.fc.entity.TbUser;
import com.fc.service.UserService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ModelAndView login(TbUser user,
                              Integer remember,
                              HttpSession session,
                              HttpServletResponse response,
                              ModelAndView mv) {

        ResultVO vo = userService.login(user.getUsername(), user.getPassword());

        if (vo.getCode() == 0) {
            // login fail
            mv.addObject("resultInfo", vo);

            mv.setViewName("forward:/login.jsp");
        } else {
            // login success
            session.setAttribute("user", vo.getData());

            Cookie cookie;

            if (remember != null && remember == 1) {
                cookie = new Cookie("JSESSIONID", session.getId());

                cookie.setMaxAge(30 * 60);
            } else {
                cookie = new Cookie("JSESSIONID", null);

                cookie.setMaxAge(-1);
            }

            response.addCookie(cookie);

            mv.setViewName("forward:/index.jsp");
        }

        return mv;
    }
}
