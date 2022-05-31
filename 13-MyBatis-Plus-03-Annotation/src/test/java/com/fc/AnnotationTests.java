package com.fc;

import com.fc.dao.AccountDao;
import com.fc.dao.StudentDao;
import com.fc.entity.Account;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class AnnotationTests {
    @Autowired
    protected AccountDao accountDao;
    @Autowired
    private StudentDao studentDao;

    @Test
    void testList() {
        List<Account> accounts = accountDao.selectList(null);

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    void testTableField() {
        List<Student> students = studentDao.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // UPDATE mybatis_plus.t_account SET availability=1 WHERE t_id=? AND availability=0
    @Test
    void testLogicDelete() {
        int affectedRows = accountDao.deleteById(1);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    // 测试插入
    @Test
    void testInsert() {
        Student student = new Student();
        // 只要手动设置了id，就不会走雪花算法生成id了
        // 自增长id会随着最大的id去增长
        //student.setId(200);
        student.setName("某踏");
        student.setGender("男");
        student.setAge(100);
        student.setBirthday(new Date());
        student.setInfo("擦边~");

        int affectedRows = studentDao.insert(student);

        System.out.println("受影响的行数：" + affectedRows);
        System.out.println("自增长的主键id：" + student.getId());
    }

    // 测试生成UUID
    @Test
    void testUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }
}
