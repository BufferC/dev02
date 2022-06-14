package com.fc.test;

import com.fc.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisUtilTests {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test() {
        redisUtil.set("age", 22, 60);

        // 先判断是否存在，如果存在再去get获取，不存在就算了
        if (redisUtil.exists("age")) {
            Object age = redisUtil.get("age");
            System.out.println(age);
        }
    }
}
