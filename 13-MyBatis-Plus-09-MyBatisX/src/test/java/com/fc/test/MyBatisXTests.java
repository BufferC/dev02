package com.fc.test;

import com.fc.dao.StudentMapper;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisXTests {
    @Autowired
    private StudentMapper studentDao;

    @Test
    void test() {
        List<Student> students = studentDao.selectAgeAndInfoAndMoodById(1L);

        students.forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        int affectedRows = studentDao.updateInfoAndNameAndAgeById("通过MyBatisX进行修改", "MyBatisX", 20, 5L);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }
}
