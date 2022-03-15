package com.fc.test;

import com.fc.entity.Student;
import com.fc.entity.Teacher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetTest {
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = applicationContext.getBean(Student.class);

        System.out.println(student);

        Teacher teacher = applicationContext.getBean(Teacher.class);

        System.out.println(teacher);
    }
}
