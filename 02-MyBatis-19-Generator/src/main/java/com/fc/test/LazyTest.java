package com.fc.test;

import com.fc.dao.StudentMapper;
import com.fc.entity.Student;
import com.fc.entity.StudentExample;
import com.fc.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class LazyTest {
    @Test
    public void test() {
        StudentMapper mapper = MyBatisUtil.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();

        StudentExample.Criteria criteria = example.createCriteria();

        criteria.andAgeNotEqualTo((byte) 20);

        List<Student> students = mapper.selectByExampleWithBLOBs(example);

        System.out.println(students);

    }
}
