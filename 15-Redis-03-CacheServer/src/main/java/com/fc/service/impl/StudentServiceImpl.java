package com.fc.service.impl;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import com.fc.service.StudentService;
import com.fc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Student> findAll() {
        // 如果缓存有数据，那么直接从缓存中去获取即可
        if (redisUtil.exists("findAll")) {
            return (List<Student>) redisUtil.get("findAll");
        }

        // 如果缓存中没有，那么就需要连接数据库
        List<Student> students = studentDao.selectList(null);

        // 再添加到缓存
        redisUtil.set("findAll", students, 60 * 60);

        return students;
    }

    @Override
    public Student insert(Student student) {
        int affectedRows = studentDao.insert(student);

        if (affectedRows > 0) {
            // 增删改操作后清空缓存即可
            redisUtil.del("findAll");
            return student;
        }

        return null;
    }

    @Override
    public Student findById(Integer id) {
        // 查询缓存中有没有
        if (redisUtil.exists("student:" + id)) {
            return (Student) redisUtil.get("student:" + id);
        }

        // 连接数据库
        Student student = studentDao.selectById(id);

        // 把数据添加到缓存中
        redisUtil.set("student:" + id, student, 60 * 60);

        return student;
    }

    @Override
    public Student update(Student student) {
        int affectedRows = studentDao.updateById(student);

        // 修改成功
        if (affectedRows > 0) {

            // 因为做了修改操作，所以所有的和修改相关的缓存都应该被删除
            redisUtil.del("student:" + student.getId());
            redisUtil.del("findAll");

            return findById(student.getId());
        }

        return null;
    }
}
