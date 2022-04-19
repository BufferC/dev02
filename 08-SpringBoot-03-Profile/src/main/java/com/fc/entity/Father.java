package com.fc.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@ConfigurationProperties("student")
@Data
@Component
public class Father implements Student {
    private String name;
    private Integer age;
}
