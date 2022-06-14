package com.fc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Redis工具类，需要依赖Spring容器
@Configuration
public class RedisUtil {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    // 从容器中获取lettuce连接工厂
    @Autowired
    private LettuceConnectionFactory factory;

    // 会在@Autowired执行之后执行此方法
    @PostConstruct
    public void init() {
        // 设置客户端连接工厂，每次获取连接都是从工厂中获取的
        redisTemplate.setConnectionFactory(factory);

        // 对模板对象进行初始化操作
        redisTemplate.afterPropertiesSet();

        // 设置序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
    }

    /**
     * 设置String类型的键值
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置string键值的同时添加过期时间，单位为s
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间，单位为s
     */
    public void set(String key, Object value, Integer expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 根据string中的key获取对应的value
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断指定的key是否存在
     *
     * @param key 键
     * @return 存在返回true
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除指定的key
     * @param key 键
     * @return 是否删除成功
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除包含了指定内容的所有key
     * @param content 内容
     * @return 删除的个数
     */
    public Long delKeys(String content) {
        Set<Object> keys = redisTemplate.keys("*" + content + "*");
        if (keys != null) {
            return redisTemplate.delete(keys);
        }
        return 0L;
    }

    /**
     * 给指定key添加过期时间
     * @param key 键
     * @param timeout 过期时间
     * @return 是否添加成功
     */
    public Boolean expire(String key, Long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取包含指定内容的全部键的个数
     * @param pattern 指定内容
     * @return 个数
     */
    public int size(String pattern) {
        Set<Object> keys = redisTemplate.keys("*" + pattern + "*");

        return keys != null ? keys.size() : 0;
    }

    /**
     * 设置hash类型
     * @param key 键
     * @param map map
     */
    public void hSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 设置hash中的键所对应的filed和value
     * @param key 键
     * @param field 字段
     * @param value 值
     */
    public void hSet(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 获取hash中指定key的field对应的value值
     * @param key 键
     * @param field 字段
     * @return value
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }
}
