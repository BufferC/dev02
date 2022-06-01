package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.enums.Mood;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentTests {
    @Autowired
    private StudentDao studentDao;

    @Test
    void test() {
        List<Student> students = studentDao.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testInsert() {
        Student student = new Student();
        student.setName("测试enum");
        // 因为我们不知道这个mood真实所需要的枚举值是多少，所以很容易写错
        student.setMood(Mood.Angry);

        int affectedRows = studentDao.insert(student);

        System.out.println(affectedRows > 0 ? "插入成功" : "插入失败");
    }
}
