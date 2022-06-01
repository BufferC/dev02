package com.fc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

// 枚举的写法
@Getter
public enum Gender {
    MALE(0), FEMALE(1);

    // 匹配枚举所对应的数据类型
    @EnumValue
    private final Integer gender;

    Gender(Integer gender) {
        this.gender = gender;
    }
}
