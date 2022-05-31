package com.fc.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 之前分页都是使用的分页插件来实现的。
// 现在MyBatis-plus有自己封装好的分页操作
@SpringBootTest
public class PageTests {
    @Autowired
    private StudentDao studentDao;
    @Test
    void test() {
        // 分页对象
        Page<Student> page = new Page<>(2, 3);

        // 分页查询
        studentDao.selectPage(page, null);

        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示条数：" + page.getSize());
        System.out.println("总查询出来的数据：" + page.getRecords());
        System.out.println("是否还有上一页：" + page.hasPrevious());
        System.out.println("是否还有下一页：" + page.hasNext());
    }

    @Test
    void testPageHelper() {
        // 开启分页
        PageHelper.startPage(4, 3);

        // 分页查询
        List<Student> students = studentDao.selectList(null);

        PageInfo<Student> pageInfo = new PageInfo<>(students);

        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("每页显示条数：" + pageInfo.getSize());
        System.out.println("总查询出来的数据：" + pageInfo.getList());
        System.out.println("是否还有上一页：" + pageInfo.isHasPreviousPage());
        System.out.println("是否还有下一页：" + pageInfo.isHasNextPage());
    }

    @Test
    void testCustomPageSQL() {
        // 分页对象
        Page<Student> page = new Page<>(1, 3);

        // 分页查询
        studentDao.findGreaterThanAgeByPage(page, 10);

        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示条数：" + page.getSize());
        System.out.println("总查询出来的数据：" + page.getRecords());
        System.out.println("是否还有上一页：" + page.hasPrevious());
        System.out.println("是否还有下一页：" + page.hasNext());
    }
}
