package com.fc.test;

import com.fc.util.JwtUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JwtUtilTest {
    @Test
    public void testGetToken() {
        Map<String, Object> claim = new HashMap<>();

        claim.put("id", "001");
        claim.put("username", "feilong");

        String token = JwtUtil.getToken(claim, "very beautiful");

        System.out.println(token);
    }

    @Test
    public void testVerifyToken() {
        Map<String, Object> result = JwtUtil.verify("eyJ0eXAiOiAiSldUIiwgImFsZyI6ICJIUzI1NiJ9.eyJpc3MiOiJ6aWhlIiwiaWQiOiIwMDEiLCJleHAiOjE2NDkyMzgyOTc0NjUsImlhdCI6MTY0OTIzODI3NzQ2NSwidXNlcm5hbWUiOiJmZWlsb25nIn0=.69961bb3df1c9dd916d97360258e4cd5", "very beautiful");

        Set<Map.Entry<String, Object>> entries = result.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
