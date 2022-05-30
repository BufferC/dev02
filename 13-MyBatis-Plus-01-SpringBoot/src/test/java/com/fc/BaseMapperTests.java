package com.fc;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

// 通用Mapper测试
// 掌握一半了
@SpringBootTest
class BaseMapperTests {
    @Autowired
    private StudentDao studentDao;

    // 测试查询全部
    @Test
    void testFindAll() {
        List<Student> students = studentDao.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // 测试根据id查询
    @Test
    void testFindById() {
        Student student = studentDao.selectById(1);

        System.out.println(student);
    }

    // 测试根据id批量查询
    //  SELECT id,name,age,gender,info FROM student WHERE id IN ( ? , ? , ? , ? )
    @Test
    void testFindByIdOnBatch() {
        //Set<Integer> set = new HashSet<>();
        //set.add(1);
        //set.add(2);
        //set.add(3);
        //set.add(4);
        //
        //List<Student> students = studentDao.selectBatchIds(set);

        // Arrays.asList(T... t)：将数组转为集合
        List<Student> students = studentDao.selectBatchIds(Arrays.asList(1, 2, 3, 4));

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // 测试查询总记录数
    @Test
    void testFindCount() {
        Long count = studentDao.selectCount(null);

        System.out.println("总数据量：" + count);
    }

    // 简单条件查询
    // SELECT id,name,age,gender,info FROM student WHERE gender = ? AND name = ?
    @Test
    void testFindMap() {
        // 添加条件
        Map<String, Object> conditional = new HashMap<>();
        // 性别为男
        conditional.put("gender", "男");
        // 姓名为易烊千玺
        conditional.put("name", "易烊千玺");

        List<Student> students = studentDao.selectByMap(conditional);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // 测试插入
    @Test
    void testInsert() {
        Student student = new Student();
        student.setName("唔西迪西");
        student.setAge(2);
        student.setGender("女");
        student.setBirthday(new Date());
        student.setInfo("唔西迪西");
        int affectedRows = studentDao.insert(student);

        // 之前的时候，我们一般会配置两个参数来实现主键的回填
        //
        System.out.println(affectedRows > 0 ? "插入成功~" : "插入失败");
        System.out.println("生成的主键id为：" + student.getId());
    }

    // 测试根据id进行删除
    // DELETE FROM student WHERE id=?
    @Test
    void testDeleteById() {
        int affectedRows = studentDao.deleteById(1735090177);
        System.out.println(affectedRows > 0 ? "删除成功~" : "删除失败");
    }

    // 测试根据id进行删除【比较鸡肋的】
    // DELETE FROM student WHERE id=?
    @Test
    void testDeleteByObjectId() {
        Student student = new Student();
        student.setId(1428860930);
        int affectedRows = studentDao.deleteById(student);

        System.out.println(affectedRows > 0 ? "删除成功~" : "删除失败");
    }

    // 简单的多条件删除，必须满足所有条件才可以
    // DELETE FROM student WHERE name = ? AND age = ?
    @Test
    void testDeleteByConditional() {
        // 多条件删除，要求必须满足所有的条件才可以
        // 用的是and拼接的
        Map<String, Object> map = new HashMap<>();
        map.put("name", "古力娜扎");
        map.put("age", 30);

        int affectedRows = studentDao.deleteByMap(map);

        System.out.println(affectedRows > 0 ? "删除成功~" : "删除失败");
    }

    // 批量删除
    // DELETE FROM student WHERE id IN ( ? , ? , ? )
    // 只要能删掉一个就算成功
    @Test
    void testBatchDelete() {
        int affectedRows = studentDao.deleteBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(affectedRows > 0 ? "删除成功~" : "删除失败");
    }

    // 测试根据id进行修改
    // UPDATE student SET age=?, info=? WHERE id=?
    @Test
    void testUpdate() {
        Student student = new Student();
        student.setId(19644418);
        student.setInfo("这是通过update修改过后的info");
        student.setAge(16);

        int affectedRows = studentDao.updateById(student);
        System.out.println(affectedRows > 0 ? "修改成功~" : "修改失败");
    }
}
