package com.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WrapperTests {
    @Autowired
    private StudentDao studentDao;

    @Test
    void testInsert() {
        Student student = new Student();
        student.setName("空空");

        int affectedRows = studentDao.insert(student);

        System.out.println(affectedRows > 0 ? "添加成功" : "添加失败");
    }

    @Test
    void contextLoads() {
        // 声明一个条件构造器
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.gt("age", "20") // 年龄大于20岁
                .eq("gender", "女") // 性别为女
                .lt("age", "100") // 年龄小于100岁
                .le("id", "10000000") // id小于等于10000000
                .ge("id", "1") // id大于等于1
                .between("age", "18", "28") // 年龄在18到28之间
                .like("name", "易") // 模糊查询
                .in("id", 1, 2, 3) // 枚举查询，id为1、2、3的符合要求
                .groupBy("id") // 根据id进行分组
                .having("id > 30") // 分组过滤
                .orderByAsc("id") // 根据id进行升序
                .orderByDesc("age") // 根据age进行降序
                .isNotNull("age"); // 年龄为null

        List<Student> students = studentDao.selectList(wrapper);

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
