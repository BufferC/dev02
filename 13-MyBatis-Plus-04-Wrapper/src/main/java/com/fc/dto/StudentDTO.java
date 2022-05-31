package com.fc.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
// 刚刚报错的原因是因为表名没有匹配上：student_d_t_o
public class StudentDTO {
    private Integer age;
    private Integer ageCount;
}
