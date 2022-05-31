package com.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

// 测试子查询相关的
// 子查询其实就是把查询结果（sub）当做条件进行第二次查询（parent）
// 单行单列
// 单行多列
// 多行多列【派生表】
@SpringBootTest
public class SubQueryTests {
    @Autowired
    private StudentDao studentDao;

    // 单行单列
    @Test
    void testSpecificField() {
        // 查询比男生的最小年龄大的所有女生信息
        // 查的就是女生，年龄比男生最小年龄大的
        // 1、先把最小的男生年龄给查出来【单行单列】
        // SELECT id,name,age,gender,birthday,info FROM student
        // WHERE (age > (select min(age) from student where gender = '男'))
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.eq("gender", "女")
                .gtSql("age", "select min(age) from student where gender = '男'"); // 子查询

        List<Student> students = studentDao.selectList(wrapper);

        students.forEach(System.out::println);
    }

    // 多行单列
    @Test
    void testSubQueryMultiRow() {
        // 查询性别为男的最大学生年龄
        // 1、性别为男
        // 2、把1的查询结果当做参数再查年龄
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.inSql("age", "select age from student where gender = '男'")
                .select("max(age)");

        List<Map<String, Object>> maps = studentDao.selectMaps(wrapper);

        maps.forEach(System.out::println);
    }

    // 派生表【先不说】
}
