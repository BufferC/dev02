package com.fc.config;

import com.fc.entity.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class BeanConfig {


    @Bean("car")
    @ConditionalOnMissingBean({Car.class, Bike.class})
    @ConditionalOnBean(Person.class)
    @Order(1)
    public Car getCar() {
        Car car = new Car();

        car.setBrand("BYD");

        return car;
    }

    @Bean("car2")
    public Car getCar2() {
        Car car = new Car();

        car.setBrand("特斯拉");

        return car;
    }

    //@Bean("wife")
    public Wife getWife() {
        return new Wife();
    }

    @Bean("son")
    // 如果容器中有Wife类的对象就注入Son
    @ConditionalOnBean(Wife.class)
    public Son getSon() {
        return new Son();
    }

    @Bean("girlFriend")
    // 如果没有老婆就可有女朋友
    @ConditionalOnMissingBean(Wife.class)
    public GirlFriend getGirlFriend() {
        return new GirlFriend();
    }
}
