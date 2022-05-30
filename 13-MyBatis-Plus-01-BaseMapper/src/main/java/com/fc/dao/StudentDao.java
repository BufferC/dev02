package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

// 通用Mapper
@Repository
public interface StudentDao extends BaseMapper<Student> {
    // 根据年龄进行降序排序
    List<Student> findByAgeOnDescOrder();
}
