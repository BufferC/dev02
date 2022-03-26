package com.fc.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.vo.ResultVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CacheInterceptor implements HandlerInterceptor {

    // 在访问Controller之前先查询缓存，如果能查到就直接返回了
    // 查不到再执行控制器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("查询缓存....");

        HttpSession session = request.getSession(false);

        // 如果session为null，说明是第一次访问，直接放行
        if (session == null) {
            return true;
        }

        Object foods = session.getAttribute("foods");

        // 不是第一次访问，但是是第一次执行控制器，直接放行
        if (foods == null) {
            return true;
        }

        System.out.println("查到了缓存");

        ResultVO vo = new ResultVO(200, "查到了缓存", true, foods);

        // 把缓存中的内容转为json字符串
        String json = new ObjectMapper().writeValueAsString(vo);

        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(json);

        return false;
    }

    // 将查询的结果放到缓存中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            Map<String, Object> model = modelAndView.getModel();

            Object foods = model.get("foods");

            System.out.println("放到缓存中...");

            request.getSession(true).setAttribute("foods", foods);

            ResultVO vo = new ResultVO(200, "没有查到缓存", true, foods);

            // 把缓存中的内容转为json字符串
            String json = new ObjectMapper().writeValueAsString(vo);

            response.setContentType("application/json; charset=UTF-8");

            response.getWriter().write(json);
        }
    }
}
