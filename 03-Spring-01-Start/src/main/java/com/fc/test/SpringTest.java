package com.fc.test;

import com.fc.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void test() {
        // 获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取对象
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.findAll();
    }
}
