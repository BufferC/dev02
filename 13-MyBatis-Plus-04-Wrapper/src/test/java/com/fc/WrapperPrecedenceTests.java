package com.fc;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;

// 测试条件优先级
@SpringBootTest
public class WrapperPrecedenceTests {
    @Autowired
    private StudentDao studentDao;

    // 匿名内部类的写法
    @Test
    void testAnon() {
        // 姓名为易烊千玺，并且（年龄大于30岁或者info为null）
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();

        wrapper.eq("name", "易烊千玺") // 姓名为易烊千玺
                // 添加第二个条件，需要传递一个Consumer的接口实现类，或者使用Lambda表达还是的形式
                // Lambda中的方法参数为Wrapper的子类，所以可以直接使用并添加条件
                .and(new Consumer<UpdateWrapper<Student>>() {
                    @Override
                    public void accept(UpdateWrapper<Student> studentUpdateWrapper) {
                        studentUpdateWrapper.gt("age", 30) // 年龄大于30岁
                                    .or() // 或者
                                    .isNull("info"); // info为null
                    }
                })
                .set("info", "测试and方法传递Lambda的方式");

        int affectedRows = studentDao.update(null, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }

    // Lambda表达式的写法
    @Test
    void test() {
        // 姓名为易烊千玺，并且（年龄大于30岁或者info为null）
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();

        wrapper.eq("name", "易烊千玺") // 姓名为易烊千玺
                // 添加第二个条件，需要传递一个Consumer的接口实现类，或者使用Lambda表达还是的形式
                // Lambda中的方法参数为Wrapper的子类，所以可以直接使用并添加条件
                .and(c -> {
                    c.gt("age", 30) // 年龄大于30岁
                            .or() // 或者
                            .isNull("info"); // info为null
                })
                .set("info", "测试and方法传递Lambda的方式");

        int affectedRows = studentDao.update(null, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }
}
