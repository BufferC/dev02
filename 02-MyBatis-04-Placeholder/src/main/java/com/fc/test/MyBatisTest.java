package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testFindById() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            List<Student> students = studentDao.findById("1 or 1 = 1");

            for (Student student : students) {
                System.out.println(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByAge() {
        SqlSession session = null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            List<Student> students = studentDao.findByAge("30 or 1 = 1");

            for (Student student : students) {
                System.out.println(student);
            }

            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
