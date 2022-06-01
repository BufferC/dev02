package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Student;
import org.springframework.stereotype.Repository;

// 通用Mapper
@Repository
public interface StudentDao extends BaseMapper<Student> {
    }
