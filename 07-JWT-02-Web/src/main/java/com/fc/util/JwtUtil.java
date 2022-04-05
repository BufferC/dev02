package com.fc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String ALG = "HS256";
    private static final String TYP = "JWT";
    private static final Map<String, Object> HEADER = new HashMap<>();
    private static final Algorithm ALGORITHM;
    private static final String SALT = "yyqxyyds";

    static {
        HEADER.put("alg", ALG);
        HEADER.put("typ", TYP);
        ALGORITHM = Algorithm.HMAC256(SALT);
    }

    // 创建token
    public static String getToken(Map<String, Object> claim, int expire) {
        return JWT.create()
                .withHeader(HEADER)
                .withClaim("claim", claim)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * expire))
                .sign(ALGORITHM);
    }

    // 验证token
    public static DecodedJWT verify(String token) {
        return JWT.require(ALGORITHM).build().verify(token);
    }

    // 获取载荷
    public static Map<String, Object> getClaim(String token) {
        return verify(token).getClaim("claim").asMap();
    }
}
