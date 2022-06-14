package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.dao.UserDao;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.util.RedisUtil;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserDao userDao;

    @Override
    public ResultVO getVerifyCode(String username) {
        ResultVO vo = new ResultVO();

        // 先查看缓存中有没有
        if (redisUtil.exists("code:" + username)) {
            vo.setMessage("请勿重复获取验证码！！！");
            vo.setSuccess(false);
            vo.setCode(-1);
            return vo;
        }

        // 如果Redis中不存在user这个键的时候再连接数据库
        if (!redisUtil.exists("user:" + username)) {
            // 连接数据库，获取用户对象，用于验证操作
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);

            User user = userDao.selectOne(wrapper);

            if (user == null) {
                vo.setMessage("当前用户不存在！！！");
                vo.setSuccess(false);
                vo.setCode(-2);
                return vo;
            }

            // 将用户对象和重试次数进行存储
            redisUtil.hSet("user:" + username, "obj", user);
            redisUtil.hSet("user:" + username, "retry", 5);
            redisUtil.expire("user:" + username, 60L * 5);
        }

        // 获取验证码
        String code = String.valueOf(Math.random()).substring(2, 8);

        vo.setData(code);
        vo.setSuccess(true);
        vo.setCode(200);

        // 存储到缓存中，过期时间为60s
        redisUtil.set("code:" + username, code, 60);

        return vo;
    }

    @Override
    public ResultVO login(String username, String password, String code) {
        ResultVO vo = new ResultVO();

        // 获取缓存中的验证码
        Object cacheCode = redisUtil.get("code:" + username);

        // 缓存中没有验证码
        if (cacheCode == null) {
            vo.setSuccess(false);
            vo.setMessage("验证码已失效，请重新获取验证码！！");
            vo.setCode(-3);
            return vo;
        }

        // 验证码不匹配
        if (!cacheCode.equals(code)) {
            vo.setSuccess(false);
            vo.setMessage("验证码错误，请重新输入验证码！！");
            vo.setCode(-4);
            return vo;
        }

        // 判断是否被锁定
        if (redisUtil.exists("locked:" + username)) {
            vo.setSuccess(false);
            vo.setMessage("当前账号已经被锁定，请30分钟后再试~~");
            vo.setCode(-5);
            return vo;
        }

        // 从缓存中获取用户对象
        User user = (User) redisUtil.hGet("user:" + username, "obj");

        // 从缓存中获取重试次数
        int retry = (int) redisUtil.hGet("user:" + username, "retry");

        // 如果密码不相同
        if (!user.getPassword().equals(password)) {
            // 如果没有了重试机会
            if (--retry == 0) {
                // 该用户被锁定
                redisUtil.set("locked:" + username, true, 30 * 60);
                vo.setMessage("重试次数过多，该用户已经被锁定，请稍后重试~~");
            } else {
                redisUtil.hSet("user:" + username, "retry", retry);
                vo.setMessage("密码错误，当前还有" + retry + "次重试机会~");
            }

            vo.setCode(-5);
            return vo;
        }

        // 能到这里说明登录成功
        vo.setSuccess(true);
        vo.setCode(200);
        vo.setMessage("登录成功了！！！！");

        // 刷新用户的失效时间，删除验证码
        redisUtil.expire("user:" + username, 60L * 30);
        redisUtil.del("code:" + username);

        return vo;
    }
}
