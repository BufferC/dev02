package com.fc.component;

import com.fc.util.ApplicationContextUtil;
import com.fc.util.RedisUtil;
import org.apache.ibatis.cache.Cache;

public class RedisCache implements Cache {

    private final String id;

    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    // 用于缓存的存储
    @Override
    public void putObject(Object key, Object value) {
        RedisUtil redisUtil = ApplicationContextUtil.getBean(RedisUtil.class);

        // 注意，这里我们使用的hSet，因为默认情况下是用map来进行存储的，这个map是和整个命名空间（id）进行关联的
        redisUtil.hSet(id, key.toString(), value);
    }

    // 用于获取缓存
    @Override
    public Object getObject(Object key) {
        RedisUtil redisUtil = ApplicationContextUtil.getBean(RedisUtil.class);
        return redisUtil.hGet(id, key.toString());
    }

    // 不用考虑，回滚的时候用的
    @Override
    public Object removeObject(Object key) {
        return null;
    }

    // 用于清空缓存，在增删改操作执行时会执行此方法
    @Override
    public void clear() {
        RedisUtil redisUtil = ApplicationContextUtil.getBean(RedisUtil.class);

        redisUtil.del(id);
    }

    @Override
    public int getSize() {
        RedisUtil redisUtil = ApplicationContextUtil.getBean(RedisUtil.class);

        return redisUtil.size(id);
    }
}
