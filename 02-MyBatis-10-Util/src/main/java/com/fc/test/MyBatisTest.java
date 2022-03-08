package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class MyBatisTest {
    @Test
    public void test() {
        try {
            StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

            List<Student> students = studentDao.findAll();

            for (Student student : students) {
                System.out.println(student);
            }

            // 成功就提交
            MyBatisUtil.commit();
        } catch (Exception e) {
            // 失败回滚
            MyBatisUtil.rollback();
            e.printStackTrace();
        }

    }
}
