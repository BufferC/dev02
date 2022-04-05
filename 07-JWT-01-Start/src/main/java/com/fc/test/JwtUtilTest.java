package com.fc.test;

import com.fc.util.JwtUtil;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JwtUtilTest {
    @Test
    public void testGet() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("username", "易烊千玺");
        claim.put("lastAccessTime", new Date());

        String token = JwtUtil.getToken(claim, 20);

        System.out.println(token);
    }

    @Test
    public void testVerify() {
        Map<String, Object> map = JwtUtil.getClaim("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjbGFpbSI6eyJsYXN0QWNjZXNzVGltZSI6MTY0OTEyMTc5MDg2NywidXNlcm5hbWUiOiLmmJPng4rljYPnjroifSwiZXhwIjoxNjQ5MTIxODExfQ.W1zC4yIuj-Lj9VS9UHvh7t_1sGaZsdlNmrKAltnTUpk");

        Set<Map.Entry<String, Object>> entries = map.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
