package com.fc.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String token = request.getHeader("token");

        response.setContentType("application/json; charset=UTF-8");

        // 转换器，把对象转为json字符串
        ObjectMapper objectMapper = new ObjectMapper();

        // 我们需要转换的对象
        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("success", false);
        map.put("data", null);

        // 如果token不存在，直接拦截下来，不能继续走controller
        if (token == null || token.equals("")) {
            map.put("message", "token不存在，访问被拒绝，请重新登录");

            // 对象转json字符串
            String json = objectMapper.writeValueAsString(map);

            response.getWriter().write(json);

            return false;
        } else {
            // 能走到这一行，说明token肯定传过来，但是不一定是对的
            try {
                // 验证token是否正确，如果验证失败

                // 解析token并获取载荷
                DecodedJWT decode = JWT.decode(token);

                Claim saltClaim = decode.getClaim("salt");

                // 获取盐值
                String salt = saltClaim.asString();

                // 验证token
                JWT.require(Algorithm.HMAC256(salt))
                        .build().verify(token);

                // 放行，执行处理器（控制器）
                return true;
            } catch (AlgorithmMismatchException e) {
                map.put("message", "算法不匹配");
            } catch (InvalidClaimException e) {
                map.put("message", "非法载荷");
            } catch (TokenExpiredException e) {
                map.put("message", "token已过期");
            } catch (SignatureVerificationException e) {
                map.put("message", "签名验证失败");
            } catch (Exception e) {
                map.put("message", "token有问题");
            }
        }

        String json = objectMapper.writeValueAsString(map);

        response.getWriter().write(json);

        // json解析失败，拦截下来
        return false;
    }
}
