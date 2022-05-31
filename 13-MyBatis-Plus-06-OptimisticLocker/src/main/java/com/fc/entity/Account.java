package com.fc.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Account {
    private Long id;
    private String username;
    private String password;
    private Double money;
    @Version // 乐观锁注解
    private Integer version;
}
