package com.fc.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.util.RedisUtil;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResultVO vo = new ResultVO();
        vo.setSuccess(false);

        System.out.println("jwt拦截器被执行了");

        // 请请求参数中获取token
        String token = request.getParameter("token");

        // 设置响应编码集和类型
        response.setContentType("application/json; charset=UTF-8");

        // token不能为空
        if (!StringUtils.hasLength(token)) {
            vo.setMessage("token为空");

        } else {
            try {
                DecodedJWT decode = JWT.decode(token);

                String phone = decode.getClaim("phone").asString();

                // token已经过期了
                if (!redisUtil.exists("token:" + phone)) {
                    vo.setMessage("token已失效");
                } else {
                    // 获取缓存中存储的签名
                    String sign = (String) redisUtil.get("sign:" + phone);

                    Verification require = JWT.require(Algorithm.HMAC256(sign));

                    require.build().verify(token);

                    // token验证后没有问题，进行放行
                    return true;
                }
            } catch (AlgorithmMismatchException e) {
                vo.setMessage("算法不匹配异常~");
            }catch (InvalidClaimException e) {
                vo.setMessage("非法载荷");
            } catch (TokenExpiredException e) {
                vo.setMessage("token已过期");
            } catch (SignatureVerificationException e) {
                vo.setMessage("签名验证失败");
            } catch (Exception e) {
                vo.setMessage("token有问题");
            }
        }

        response.getWriter().print(objectMapper.writeValueAsString(vo));
        return false;
    }
}
