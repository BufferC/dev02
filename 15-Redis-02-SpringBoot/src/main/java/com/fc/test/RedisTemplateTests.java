package com.fc.test;

import com.fc.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class RedisTemplateTests {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @BeforeEach
    void before() {
        // 设置key的序列化方式为字符串
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式为json
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
    }

    @Test
    void testSet() {
        ValueOperations<Object, Object> value = redisTemplate.opsForValue();

        Student student = new Student();
        student.setId(2L);
        student.setName("迪丽热巴");
        student.setGender("女");
        student.setAge(30);

        value.set("student1", student);
    }

    @Test
    void testGet() {
        Object student1 = redisTemplate.opsForValue().get("student1");
        System.out.println(student1);
    }

    @Test
    void testConnect() {
        Set<Object> keys = redisTemplate.keys("*");
        keys.forEach(System.out::println);
    }

    @Test
    void testHash() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "易烊千玺");
        map.put("age", 22);
        map.put("gender", "男");

        redisTemplate.opsForHash().putAll("student", map);


        Set<Object> student = redisTemplate.opsForHash().keys("student");
        student.forEach(System.out::println);
    }
}
