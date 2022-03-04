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
    public void testFindStudentById() {

        try {
            // 读取配置文件到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            // 构建会话工厂
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            // 获取连接
            SqlSession session = factory.openSession();

            // 获取接口的实现类
            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = studentDao.findStudentById(3);

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
            // 读取配置文件，获取流
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            // 构建工厂
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            // 获取连接
            SqlSession session = factory.openSession();

            // 获取接口的实现类
            StudentDao studentDao = session.getMapper(StudentDao.class);

            List<Student> students = studentDao.findAll();

            for (Student student : students) {
                System.out.println(student);
            }

            // 提交事务
            session.commit();

            // 关闭资源
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInterface() {
        // 读取配置文件
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            // 构建会话工厂
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            // 获取连接
            SqlSession session = factory.openSession();

            // 通过反射获取接口的实现类
            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = studentDao.findById();

            System.out.println(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {

        try {
            // 读取配置文件到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            // 构建SqlSession工厂
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

            // 生产SqlSession->QueryRunner、Connection
            SqlSession session = factory.openSession();

            // 执行sql语句获取受影响的行数
            int affectedRows = session.insert("StudentMapper.insert");

            System.out.println("受影响的行数：" + affectedRows);

            // 提交事务
            session.commit();

            // 关闭资源
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            // 读取配置文件中的内容到流中
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            // 获取MyBatis核心类对象SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 通过会话工厂获取连接，相当于queryRunner
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //sqlSession
            Object o = sqlSession.selectOne("StudentMapper.select");

            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
