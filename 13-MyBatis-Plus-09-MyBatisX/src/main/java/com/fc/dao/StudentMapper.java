package com.fc.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Buffer
* @description 针对表【student(学生表)】的数据库操作Mapper
* @createDate 2022-06-01 15:47:48
* @Entity com.fc.entity.Student
*/
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> selectAgeAndInfoAndMoodById(@Param("id") Long id);

    int updateInfoAndNameAndAgeById(@Param("info") String info, @Param("name") String name, @Param("age") Integer age, @Param("id") Long id);
}




