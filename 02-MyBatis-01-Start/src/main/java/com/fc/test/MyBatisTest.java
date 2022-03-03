package com.fc.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
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
