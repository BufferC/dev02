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
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testGetIncrement() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = new Student();
            student.setName("狂拽酷炫霸");
            student.setAge((byte) 2);
            student.setBirthday(new Date());
            student.setGender("男");
            student.setInfo("未完待续...");

            int affectedRows = studentDao.getIncrement(student);

            System.out.println("是否成功：" + affectedRows);
            System.out.println("获取自增长的id：" + student.getId());

            session.commit();

            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByKeyword() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            List<Student> students = studentDao.findByKeyword("士");

            for (Student student : students) {
                System.out.println(student);
            }

            session.commit();

            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindById() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = studentDao.findById(8);

            System.out.println(student);

            session.commit();

            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            List<Student> students = studentDao.findAll();

            for (Student student : students) {
                System.out.println(student);
            }

            session.commit();

            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateInfo() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = new Student();

            student.setInfo("睡在我下铺的兄弟");
            student.setId(8);

            int affectedRows = studentDao.update(student);

            System.out.println("受影响的行数：" + affectedRows);
            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            int affectedRows = studentDao.delete(3);

            System.out.println("受影响的行数：" + affectedRows);
            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = factory.openSession();

            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = new Student();

            student.setName("超");
            student.setAge((byte) 19);
            student.setGender("男");
            student.setBirthday(new Date());
            student.setInfo("睡在我上铺的兄弟");

            int affectedRows = studentDao.insert(student);

            System.out.println("受影响的行数：" + affectedRows);
            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
