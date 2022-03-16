package com.fc.test;

import com.fc.config.JdbcConfig;
import com.fc.dao.impl.JDBCTemplateDaoImpl;
import com.fc.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTemplateTest {
    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JdbcConfig.class);

        JDBCTemplateDaoImpl templateDao = applicationContext.getBean(JDBCTemplateDaoImpl.class);

        User user = templateDao.findById(1);

        System.out.println(user);
    }
}
