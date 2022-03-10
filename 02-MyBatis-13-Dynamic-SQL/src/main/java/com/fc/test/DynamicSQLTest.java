package com.fc.test;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class DynamicSQLTest {
    @Test
    public void testDeleteMore() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        int affectedRows = studentDao.deleteMore(7, 8, 9, 10, 5, 6);

        MyBatisUtil.commit();
    }

    @Test
    public void testUpdateWithTrim() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        Student student = new Student();
        student.setId(10);
        student.setAge((byte) 20);
        student.setName("爷傲奈我何、");

        int affectedRows = studentDao.updateWithTrim(student);

        MyBatisUtil.commit();
    }

    @Test
    public void testUpdate() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        Student student = new Student();
        student.setId(5);
        student.setAge((byte) 100);
        student.setInfo("逢魔时王");

        int affectedRows = studentDao.update(student);

        MyBatisUtil.commit();
    }

    @Test
    public void testFindStudentWithTrim() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        Student student = new Student();
        student.setGender("男");
        student.setAge((byte) 20);
        student.setId(6);

        List<Student> students = studentDao.findByStudentWithTrim(student);

        for (Student temp : students) {
            System.out.println(temp);
        }
    }

    @Test
    public void testFindStudent() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        Student student = new Student();
        student.setGender("男");
        student.setAge((byte) 20);
        student.setId(6);

        List<Student> students = studentDao.findByStudent(student);

        for (Student temp : students) {
            System.out.println(temp);
        }
    }

    @Test
    public void testFindKeyword() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        List<Student> students = studentDao.findByKeyword("%士%", 20);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testFindAll() {
        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        List<Student> students = studentDao.findAll();

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
