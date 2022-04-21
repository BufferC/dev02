package com.fc.entity;

import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Data
@Component
@Order(Integer.MIN_VALUE)
public class Car {
    private String brand = "碰碰车";
}
