package com.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fc.dao.AccountDao;
import com.fc.dao.StudentDTODao;
import com.fc.dao.StudentDao;
import com.fc.dto.StudentDTO;
import com.fc.entity.Account;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootTest
class WrapperTests {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private StudentDTODao studentDTODao;

    @Test
    void testInsert() {
        Student student = new Student();
        student.setName("空空");

        int affectedRows = studentDao.insert(student);

        System.out.println(affectedRows > 0 ? "添加成功" : "添加失败");
    }

    @Test
    void testDelete() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "某踏");
                //.isNull("id"); // id为null

        int affectedRows = studentDao.delete(wrapper);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    @Test
    void testLogicDelete() {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("t_id", 2);

        int affectedRows = accountDao.delete(wrapper);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    @Test
    void testConditionQuery() {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("t_id");

        List<Account> accounts = accountDao.selectList(wrapper);

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    void testUpdateByQueryWrapper() {
        Student student = new Student();

        student.setAge(22);
        student.setInfo("条件构造器修改过了~~");

        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.isNull("age"); // 年龄为null

        // 修改方法需要传递两个参数，分别是修改的内容以及查询条件
        int affectedRows = studentDao.update(student, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }

    // updateWrapper可以通过set方法来设置需要被修改的内容，不需要再传递一个实体类参数了。
    // 而且还支持null值
    @Test
    void testUpdateByUpdateWrapper() {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();

        wrapper.set("info", null)
                .set("age", 18)
                .isNull("birthday"); // 生日为null

        // 修改方法需要传递两个参数，分别是修改的内容以及查询条件
        int affectedRows = studentDao.update(null, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }

    @Test
    void testQuery() {
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

    // 查询指定的字段
    // 之前我们都是查询全部字段，没必要
    @Test
    void testSpecificField() {
        QueryWrapper<StudentDTO> wrapper = new QueryWrapper<>();

        wrapper.select("age", "count(age) as ageCount")
        .groupBy("age");

        List<StudentDTO> students = studentDTODao.selectList(wrapper);

        for (StudentDTO student : students) {
            System.out.println(student);
        }
    }

    // 匿名内部类
    @Test
    void testQueryMapByAnon() {
        List<Map<String, Object>> maps = studentDao.selectMaps(null);

        // 方法引用，是Lambda的简写方式
        maps.forEach(new Consumer<Map<String, Object>>() {
            @Override
            public void accept(Map<String, Object> stringObjectMap) {
                System.out.println(stringObjectMap);
            }
        });
    }

    // Lambda表达式
    @Test
    void testQueryMapByLambda() {
        List<Map<String, Object>> maps = studentDao.selectMaps(null);

        // 方法引用，是Lambda的简写方式
        maps.forEach(map -> System.out.println(map));
    }

    // 方法引用。JDK1.8的新特性
    @Test
    void testQueryMap() {
        List<Map<String, Object>> maps = studentDao.selectMaps(null);

        //for (Map<String, Object> map : maps) {
        //    map.forEach((s, o) -> System.out.println(s + o));
        //}

        // 方法引用，是Lambda的简写方式
        maps.forEach(System.out::println);
    }
}
