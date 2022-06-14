package com.fc.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fc.service.UserService;
import com.fc.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResultVO getVerifyCode(String phone) {
        ResultVO vo = new ResultVO();

        // 获取验证码
        String code = String.valueOf(Math.random()).substring(2, 8);

        vo.setData(code);
        vo.setSuccess(true);
        vo.setCode(200);

        return vo;
    }

    @Override
    public ResultVO login(String phone, String code) {
        ResultVO vo = new ResultVO();

        // 能到这里说明登录成功
        vo.setSuccess(true);
        vo.setCode(200);
        vo.setMessage("登录成功了！！！！");

        // 获取头部
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "jwt");

        // 获取随机的签名（UUID截取）
        String signature = UUID.randomUUID().toString().substring(0, 7);

        // 生成jwt
        String jwt = JWT.create()
                .withHeader(header)
                .withClaim("phone", phone)
                .sign(Algorithm.HMAC256(signature));

        Map<String, Object> data = new HashMap<>();

        data.put("phone", phone);
        data.put("jwt", jwt);
        data.put("signature", signature);

        vo.setData(data);

        return vo;
    }

    @Override
    public ResultVO logout(String token) {
        ResultVO vo = new ResultVO();
        vo.setSuccess(true);
        vo.setCode(200);
        return vo;
    }
}
