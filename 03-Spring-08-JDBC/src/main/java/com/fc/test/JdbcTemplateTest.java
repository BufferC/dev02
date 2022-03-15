package com.fc.test;

import com.fc.dao.impl.JDBCTemplateDaoImpl;
import com.fc.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTemplateTest {
    @Test
    public void testFindAll() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCTemplateDaoImpl userDao = applicationContext.getBean(JDBCTemplateDaoImpl.class);

        List<User> users = userDao.findAll();

        System.out.println(users);
    }

    @Test
    public void testFindById() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        JDBCTemplateDaoImpl userDao = applicationContext.getBean(JDBCTemplateDaoImpl.class);

        User user = userDao.findById(1);

        System.out.println(user);
    }
}
