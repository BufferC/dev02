package com.fc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void test() {
        // 加载配置文件获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        // 通过工厂来获取安全管理器
        SecurityManager securityManager = factory.getInstance();

        // 设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 通过工具类获取主体
        Subject subject = SecurityUtils.getSubject();

        // 准备一个封装了用户名和密码的token
        UsernamePasswordToken token = new UsernamePasswordToken("迪丽热巴", "123456");

        // 通过主体进行登录
        subject.login(token);

        // 判断是否认证成功
        if (subject.isAuthenticated()) {
            System.out.println("认证成功");
        } else {
            System.out.println("认证失败");
        }
    }
}
