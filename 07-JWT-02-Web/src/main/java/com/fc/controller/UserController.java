package com.fc.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.util.JwtUtil;
import com.fc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Map<String, Object> login(User user) {
        UserVO userVO = userService.login(user.getUsername(), user.getPassword());

        Map<String, Object> map = new HashMap<>();

        if (userVO != null) {
            map.put("code", 200);
            map.put("success", true);
            map.put("message", "登录成功！！");
            map.put("data", userVO);

            Map<String, Object> claim = new HashMap<>();

            claim.put("username", userVO.getUsername());
            claim.put("lastAccessTime", userVO.getLastAccessTime());

            String token = JwtUtil.getToken(claim, 20);

            map.put("token", token);
        } else {
            map.put("code", -1);
            map.put("success", false);
            map.put("message", "登录失败！！");
            map.put("data", null);
        }

        return map;
    }

    @RequestMapping("verify")
    public Map<String, Object> verify(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("success", false);
        map.put("data", null);

        if (token == null) {
            map.put("message", "token不存在，访问被拒绝");

            return map;
        }

        try {
            Map<String, Object> claim = JwtUtil.getClaim(token);

            map.put("code", 200);
            map.put("success", true);
            map.put("message", "token验证成功");
            map.put("data", claim);

            return map;
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

        return map;
    }
}
