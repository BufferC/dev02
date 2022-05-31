package com.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 测试动态条件
@SpringBootTest
public class ConditionalTests {
    @Autowired
    private StudentDao studentDao;

    // 假如说现在有个需求，前端会给我后端传递很多的参数用于做多条件模糊查询的
    // 姓名、年龄、性别
    // 而且这些参数并不一定都会传递，也不一定都不会传递
    // 你给我什么条件，我就用什么条件，没有就算了
    @Test
    void test() {
        String name = "易";
        // 因为性别一般都是单选框，所以直接使用eq就行
        String gender = "男";
        Integer startAge = 10;
        Integer endAge = 60;

        conditionalQuery(name, gender, startAge, endAge);


    }

    // 动态条件构造器，只有当前面的逻辑判断满足为true时，后面的条件才会拼接上去。
    void conditionalQuery(String name, String gender,
                  Integer startAge, Integer endAge) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper
                .like(StringUtils.isNotBlank(name), "name", name)
                .eq(StringUtils.isNotBlank(gender), "gender", gender)
                .gt(startAge != null, "age", startAge)
                .lt(endAge != null, "age", endAge);

        List<Student> students = studentDao.selectList(wrapper);

        students.forEach(System.out::println);
    }

    // SELECT id,name,age,gender,birthday,info FROM student
    // WHERE (name LIKE ? AND gender = ? AND age > ? AND age < ?)
    void rwaQuery(String name, String gender,
                          Integer startAge, Integer endAge) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        // 如果传递过来了name，那么就当做条件
        // 把姓名当做模糊查询条件
        // StringUtils.isNotBlank(name)就相当于name != null && !name.equals("")
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }

        // 指定性别
        if (StringUtils.isNotBlank(gender)) {
            wrapper.eq("gender", gender);
        }

        // 大于最小的年龄
        if (startAge != null) {
            wrapper.gt("age", startAge);
        }

        // 小于最大的年龄
        if (endAge != null) {
            wrapper.lt("age", endAge);
        }

        List<Student> students = studentDao.selectList(wrapper);

        students.forEach(System.out::println);
    }
}
