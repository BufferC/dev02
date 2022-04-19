package com.fc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
// 加载自定义的配置文件
//@PropertySource("classpath:jdbc.properties")
@PropertySource({"classpath:jdbc.properties", "classpath:person.yml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
