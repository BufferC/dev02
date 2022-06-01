package com.fc.entity;

import com.fc.enums.Gender;
import lombok.Data;

@Data
public class Person {
    private Integer id;
    private String name;
    // 使用枚举类型来存储性别
    private Gender gender;
}
