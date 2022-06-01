package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.entity.Student;
import com.fc.service.StudentService;
import com.fc.dao.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author Buffer
* @description 针对表【student(学生表)】的数据库操作Service实现
* @createDate 2022-06-01 15:47:49
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




