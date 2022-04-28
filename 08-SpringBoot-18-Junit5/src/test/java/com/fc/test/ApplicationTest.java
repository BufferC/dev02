package com.fc.test;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

// 加载Spring容器环境
@SpringBootTest
@DisplayName("Junit5测试注解类")
@Tag("直男")
public class ApplicationTest {
    @Test
    @DisplayName("测试DisplayName注解")
    void test() {
        System.out.println("测试DisplayName注解");
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("测试超时注解")
    void testTimeout() {
        try {
            Thread.sleep(3000);
            System.out.println("测试超时注解@Timeout");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Tag("暖男")
    @Test
    @DisplayName("测试@Tag标签注解")
    void testTag() {
        System.out.println("甲赛是暖男~~❤");
    }

    @Tag("渣男")
    @Test
    @DisplayName("测试@Tag标签渣男注解")
    void testTag2() {
        System.out.println("甲赛是渣男~~心碎");
    }

    @Test
    @DisplayName("测试@Tag标签直男注解")
    void testTag1() {
        System.out.println("甲赛是直男~~ ┓(;´_｀)┏");
    }

    @RepeatedTest(3)
    @Tag("暖男")
    void testRepeatedTest() {
        System.out.println("今天想吃猪蹄");
    }

    @Disabled
    @Test
    @DisplayName("测试Disabled方法")
    void testDisabled() {
        System.out.println("测试Disabled方法");
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("开始测试");
    }

    // 一般用于初始化操作
    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有的测试方法执行之前，只执行一次");
    }

    // 常用于释放资源
    @AfterAll
    static void testAfterAll() {
        System.out.println("所有的测试方法执行之后，只执行一次");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试结束");
    }
}
