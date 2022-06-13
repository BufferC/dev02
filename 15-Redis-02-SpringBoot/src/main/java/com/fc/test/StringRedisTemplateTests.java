package com.fc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class StringRedisTemplateTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testConnect() {
        // 获取所有的键
        Set<String> keys = redisTemplate.keys("*");
        keys.forEach(System.out::println);

        // 判断指定的键是否存在
        Boolean nameExists = redisTemplate.hasKey("name");
        System.out.println("判断是否存在：" + nameExists);

        DataType dataType = redisTemplate.type("贺涛的好友");
        System.out.println("获取数据类型：" + dataType.name());

        // 获取过期时间，相当于expire命令
        Long expire = redisTemplate.getExpire("name");
        System.out.println("过期时间：" + expire);
    }

    @Test
    void testString() {
        // 获取字符串类型的操作对象
        ValueOperations<String, String> stringOperations = redisTemplate.opsForValue();
        // set
        stringOperations.set("name", "易烊千玺");

        // get
        String name = stringOperations.get("name");
        System.out.println(name);

        // setex
        stringOperations.set("age", "22", 60, TimeUnit.SECONDS);

        // setpx
        stringOperations.set("gender", "男", 60000, TimeUnit.MILLISECONDS);

        // incr、incrby、incrbyfloat
        Double incrementAag = stringOperations.increment("age", 3.14);
        System.out.println("自增后的年龄：" + incrementAag);

        // setnx，Absent【缺席】
        stringOperations.setIfAbsent("info", "四个字");
        // setxx，目前现在的意思
        stringOperations.setIfPresent("name", "迪丽热巴");
        // set xx ex
        stringOperations.setIfPresent("name", "迪丽热巴", 100, TimeUnit.SECONDS);
        // set nx ex
        stringOperations.setIfAbsent("info", "四个字", 100, TimeUnit.SECONDS);
    }

    @Test
    void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        // lpush
        list.leftPushAll("food", "鱼香肉丝", "宫保鸡丁", "麻婆豆腐");

        // lrange
        List<String> food = list.range("food", 0, -1);

        food.forEach(System.out::println);

        // 相当于lset
        list.set("food", 1, "油焖大虾");

        // llen
        Long size = list.size("food");
        System.out.println(size);

        // rpop和lpop
        String leftPop = list.leftPop("food");
        String rightPop = list.rightPop("food");
        System.out.println("左边弹出来的：" + leftPop);
        System.out.println("右边弹出来的：" + rightPop);
    }

    @Test
    void testSet() {
        SetOperations<String, String> set = redisTemplate.opsForSet();

        // sadd
        set.add("男歌手", "周杰伦", "易烊千玺");
        set.add("女歌手", "王心凌", "迪丽热巴");

        // members
        Set<String> members = set.members("男歌手");
        members.forEach(System.out::println);

        // scard
        Long size = set.size("男歌手");
        System.out.println(size);

        // 并集
        Set<String> union = set.union("男歌手", "女歌手");
        union.forEach(System.out::println);

        // 取交集
        Set<String> intersect = set.intersect("男歌手", "女歌手");
        intersect.forEach(System.out::println);

        // 取差集
        Set<String> difference = set.difference("男歌手", "女歌手");
        difference.forEach(System.out::println);
    }

    @Test
    void testZSet() {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        // zadd
        zSet.add("score", "Java", 100D);
        zSet.add("score", "MySQL", 80D);
        zSet.add("score", "Redis", 60D);

        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();

        typedTuples.add(new DefaultTypedTuple<>("语文", 59D));
        typedTuples.add(new DefaultTypedTuple<>("数学", 40D));
        typedTuples.add(new DefaultTypedTuple<>("英语", 30D));

        zSet.add("score", typedTuples);

        Set<ZSetOperations.TypedTuple<String>> score = zSet.rangeWithScores("score", 0, -1);

        score.forEach(System.out::println);
    }

    @Test
    void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

        hash.put("user", "name", "易烊千玺");

        Map<String, String> map = new HashMap<>();
        map.put("age", "22");
        map.put("gender", "男");

        hash.putAll("user", map);

        Set<Object> keys = hash.keys("user");

        keys.forEach(System.out::println);

        List<Object> values = hash.values("user");

        values.forEach(System.out::println);

        Map<Object, Object> user = hash.entries("user");
        Set<Map.Entry<Object, Object>> entries = user.entrySet();
        entries.forEach(System.out::println);
    }
}
