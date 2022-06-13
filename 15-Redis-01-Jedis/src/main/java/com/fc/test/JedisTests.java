package com.fc.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.Set;

public class JedisTests {
    private static Jedis jedis;

    @Test
    public void testConnect() {
        String pong = jedis.ping("是否连接成功");

        System.out.println(pong);
    }

    // 初始化连接对象
    @BeforeClass
    public static void before() {
        // 获取redis连接
        jedis = new Jedis("192.168.204.156", 6379);
        // 设置连接密码
        jedis.auth("root");
        // 选择库
        jedis.select(0);
    }

    @AfterClass
    public static void after() {
        // 连接是一个资源，每次都要进行关闭
        jedis.close();
    }

    @Test
    public void testString() {
        Set<String> keys = jedis.keys("*");

        keys.forEach(System.out::println);

        String name = jedis.get("name");
        System.out.println("name：" + name);

        List<String> values = jedis.mget("name", "age", "gender");
        values.forEach(System.out::println);

        //String setResult = jedis.set("info", "四个字");
        //System.out.println("set操作：" + setResult);

        Boolean infoExists = jedis.exists("info");
        System.out.println("info是否存在：" + infoExists);

        Long ageIncr = jedis.incr("age");
        System.out.println("incr：" + ageIncr);

        SetParams setParams = new SetParams();
        // 相当于使用ex
        setParams.ex(20L);
        // xx只有当键存在时才能操作，相当于修改操作
        // nx只有当键不存在时才能操作，相当于添加操作
        setParams.nx();

        // set中可以使用的额外参数：nx|xx、ex、px
        String setResult = jedis.set("name", "迪丽热巴", setParams);
        System.out.println(setResult);

        // 给指定的键设置过期时间
        jedis.expire("age", 200L);

        // 获取指定键的过期时间
        Long ageTtl = jedis.ttl("age");
        System.out.println("age的过期时间：" + ageTtl);

        Long delResult = jedis.del("age", "name", "gender");
        System.out.println("是否参数成功：" + delResult);
    }

    @Test
    public void testHash() {
        jedis.hset("user", "age", "22");
        jedis.hset("user", "gender", "男");
        jedis.hset("user", "info", "四个字");
    }

    @Test
    public void testList() {
        jedis.lpush("food", "鱼香肉丝", "宫保鸡丁", "麻婆豆腐");
    }
}
