package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

// 通用Mapper
@Repository
public interface StudentDao extends BaseMapper<Student> {
    // 大于指定年龄分页
    Page<Student> findGreaterThanAgeByPage(@Param("page") Page<Student> page, @Param("age") Integer age);
}
