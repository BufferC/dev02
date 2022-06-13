package com.fc.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.entity.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

// 测试Jedis连接池
public class JedisPoolTests {
    private static JedisPool pool;
    private static Jedis jedis;

    @BeforeClass
    public static void before() {
        // 我们也可以手动设置连接池的参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大的空闲线程的数量
        config.setMaxIdle(20);
        // 最小的闲置线程的数量
        config.setMinIdle(5);
        // 最大的线程数量
        config.setMaxTotal(100);
        // 检查空闲连接是否可用的，如果连接不可用会被直接移除掉，默认就是false
        config.setTestOnBorrow(false);
        // 监控信息，默认就是true
        config.setJmxEnabled(true);
        // 资源拥挤时进行阻塞，默认就是true
        config.setBlockWhenExhausted(true);
        // 资源耗尽的等待时间，默认为-1，用不超时，设置了1s
        config.setMaxWait(Duration.ofSeconds(1));

        pool = new JedisPool(config, "192.168.204.156", 6379, 1000, "root");

        jedis = pool.getResource();
        jedis.select(0);
    }

    @Test
    public void test() {
        //jedis.sadd("贺涛的好友", "甲赛", "紫贺", "豪祥");

        Map<String, Double> score = new HashMap<>();

        score.put("Java", 100.0);
        score.put("MySQL", 80.0);
        score.put("Redis", 60.0);

        jedis.zadd("score", score);
    }

    @Test
    public void testJson() {
        try {
            Student student = new Student();
            student.setId(2L);
            student.setName("迪丽热巴");
            student.setAge(30);
            student.setGender("女");
            student.setInfo("四个字");

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(student);

            SetParams setParams = new SetParams();
            // 半个钟头
            setParams.ex(60 * 60L);
            setParams.nx(); // 不存在时才能操作

            String result = jedis.set("student:" + student.getId(), json, setParams);
            System.out.println("是否设置成功：" + result);

            // 根据键获取对应的值
            String value = jedis.get("student:" + student.getId());

            Student cacheStudent = objectMapper.readValue(value, Student.class);

            System.out.println("缓存中的对象" + cacheStudent);
            System.out.println("过期时间：" + jedis.ttl("student:" + student.getId()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void after() {
        jedis.close();
        pool.close();
    }
}
