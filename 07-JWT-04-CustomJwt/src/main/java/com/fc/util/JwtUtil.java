package com.fc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    // 获取token
    public static String getToken(Map<String, Object> claim, String salt) {
        // 添加过期时间到载荷中
        claim.put("exp", System.currentTimeMillis() + 1000L * 20);
        // 添加发布时间
        claim.put("iat", System.currentTimeMillis());
        claim.put("iss", "zihe");

        // 未编码之前的头部
        String jsonHeader = "{\"typ\": \"JWT\", \"alg\": \"HS256\"}";

        // 编码后的头部
        String header = Base64.getEncoder().encodeToString(jsonHeader.getBytes(StandardCharsets.UTF_8));

        // 获取编码前的载荷
        String jsonPayload = null;

        try {
            jsonPayload = new ObjectMapper().writeValueAsString(claim);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 获取编码后的载荷
        String payload = Base64.getEncoder().encodeToString(jsonPayload.getBytes(StandardCharsets.UTF_8));

        // 获取16进制的md5加密后的签名
        String signature = DigestUtils.md5Hex(header + "." + payload + salt);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(header)
                .append(".")
                .append(payload)
                .append(".")
                .append(signature);

        return new String(stringBuilder);
    }

    // 验证token
    public static Map<String, Object> verify(String token, String salt) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("success", false);
        map.put("data", null);

        // 分割jwt
        String[] array = token.split("\\.");

        // 获取编码后的载荷
        String payload = array[1];

        // 把载荷解码变成一个字节数组
        byte[] decode = Base64.getDecoder().decode(payload.getBytes(StandardCharsets.UTF_8));

        // 生成json字符串格式的载荷
        String jsonPayload = new String(decode);

        ObjectMapper objectMapper = new ObjectMapper();

        // Map格式的载荷
        Map<String, Object> claim = null;

        // 将
        try {
            claim = objectMapper.readValue(jsonPayload, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 获取过期时间
        Long exp = (Long) claim.get("exp");

        // 如果当前时间超过或者等于过期时间，说明已经过期
        if (System.currentTimeMillis() >= exp) {
            map.put("message", "token已经失效了");
            return map;
        }

        // 重新验证一次新的签名
        String verifiedSign = DigestUtils.md5Hex(array[0] + "." + array[1] + salt);

        // 如果验证后的签名和原始签名相同，说明验证成功
        if (verifiedSign.equals(array[2])) {
            map.put("success", true);
            map.put("message", "token验证成功");
            map.put("code", 200);
            map.put("data", claim);

            return map;
        }

        // 验证失败
        map.put("message", "token验证失败");

        return map;
    }
}
