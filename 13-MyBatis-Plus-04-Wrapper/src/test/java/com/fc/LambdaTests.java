package com.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 测试LambdaQueryWrapper和LambdaUpdateWrapper
@SpringBootTest
public class LambdaTests {
    @Autowired
    private StudentDao studentDao;

    @Test
    void testLambdaQueryWrapper() {
        //LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lambda().ge(Student::getAge, 10);

        // 年龄大于10岁
        // 如果我们想要age，就不需要自己手写了，因为手写可能会写错，
        // 通过lambda表达式直接从实体类中去获取

        List<Student> students = studentDao.selectList(wrapper);

        students.forEach(System.out::println);
    }

    @Test
    void testLambdaUpdate() {
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();

        wrapper.ge(Student::getAge, 20)
        .set(Student::getInfo, "通过Lambda表达式来修改信息");

        int affectedRows = studentDao.update(null, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }
}
