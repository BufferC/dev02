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

// 此时这个类即是一个拦截器，又是一个增强器【AOP】
@ControllerAdvice
public class GetVerifyCodeInterceptor implements ResponseBodyAdvice<ResultVO>, HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ObjectMapper objectMapper;
    private String phone;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        phone = request.getParameter("phone");
        ResultVO vo = new ResultVO();
        // 先查看缓存中有没有
        if (redisUtil.exists("code:" + phone)) {
            vo.setMessage("请勿重复获取验证码！！！");
            vo.setSuccess(false);
            vo.setCode(-1);

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(vo));

            // 拦截
            return false;
        }

        // 放行
        return true;
    }

    /**
     * 是否是我们需要增强的方法
     * @param returnType 返回值的方法参数
     * @param converterType 转换器类型
     * @return 如果是返回true。否则返回false
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 获取方法名
        String name = returnType.getMethod().getName();

        // 判断是否是获取验证码的方法
        return name.equals("getVerifyCode");
    }

    /**
     * 方法执行之后执行此方法，相当于后置增强
     *
     * @param body 返回值
     * @param returnType 方法参数
     * @param selectedContentType 选择的内容类型
     * @param selectedConverterType 选择的转换器类型
     * @param request 请求对象
     * @param response 响应对象
     * @return 真正的返回值
     */
    @Override
    public ResultVO beforeBodyWrite(ResultVO body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 获取验证码
        Object code = body.getData();

        // 存储到缓存中，过期时间为60s
        redisUtil.set("code:" + phone, code, 60);

        // 返回
        return body;
    }
}
