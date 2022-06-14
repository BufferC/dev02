package com.fc.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.util.RedisUtil;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class LoginInterceptor implements HandlerInterceptor, ResponseBodyAdvice<ResultVO> {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResultVO vo = new ResultVO();

        String phone = request.getParameter("phone");

        // 缓存中没有验证码
        if (!redisUtil.exists("code:" + phone)) {
            vo.setSuccess(false);
            vo.setMessage("验证码已失效，请重新获取验证码！！");
            vo.setCode(-3);

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(vo));
        }

        // 获取缓存中的验证码
        Object cacheCode = redisUtil.get("code:" + phone);

        // 验证码不匹配
        if (!cacheCode.equals(request.getParameter("code"))) {
            vo.setSuccess(false);
            vo.setMessage("验证码错误，请重新输入验证码！！");
            vo.setCode(-4);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(vo));
        }

        // 删除验证码
        redisUtil.del("code:" + phone);

        // 放行
        return true;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        String name = returnType.getMethod().getName();

        return name.equals("login");
    }

    @Override
    public ResultVO beforeBodyWrite(ResultVO body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Map<String, Object> data = (Map<String, Object>) body.getData();

        Object phone = data.get("phone");
        Object jwt = data.get("jwt");
        Object signature = data.get("signature");

        // 需要在缓存中放一些东西【token、签名】
        redisUtil.set("token:" + phone, jwt, 60 * 60);
        redisUtil.set("sign:" + phone, signature, 60 * 60);

        // 重置返回值中的内容
        body.setData(jwt);

        return body;
    }
}
