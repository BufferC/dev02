package com.fc.vo;

import com.fc.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {
    private String name;
}
