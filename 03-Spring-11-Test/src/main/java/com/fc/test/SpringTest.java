package com.fc.test;

import com.fc.config.SpringConfig;
import com.fc.entity.Student;
import com.fc.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 配置运行环境
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringConfig.class)
public class SpringTest {
    @Autowired
    private Student student;
    @Autowired
    private Teacher teacher;

    @Test
    public void test() {
        System.out.println(student);
        System.out.println(teacher);
    }
}
