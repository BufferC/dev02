package com.fc.test;

import com.fc.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

// 断言失败后，后面的代码还能执行吗？
@DisplayName("断言测试类")
@SpringBootTest
public class AssertTest {
    @Autowired
    private Student student1;
    @Autowired
    private Student student2;

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    @Test
    @DisplayName("简单断言是否是相同的")
    void testAssertEquals() {
        Assertions.assertEquals(3, add(1, 2), "这都能算错？");

        System.out.println("你真帅");
    }

    @Test
    @DisplayName("简单断言是否是同一个对象")
    void testAssertSame() {
        Assertions.assertNotSame(student1, student2, "你们的老婆是同一个人？");
    }

    @Test
    @DisplayName("断言是true还是false")
    void testAssertTrue() {
        Assertions.assertFalse(student1 == student2 && 1 < 0, "你们的老婆是同一个人？");
    }

    @Test
    @DisplayName("数组断言")
    void testAssertArrayEquals() {
        Assertions.assertArrayEquals(new int[] {1, 2}, new int[] {2, 1}, "不是同一个数组");
    }

    @Test
    @DisplayName("组合断言")
    void testAssertAll() {
        Assertions.assertAll(new Executable() {
            @Override
            public void execute() throws Throwable {
                Assertions.assertSame(student1, student2, "你们的老婆是同一个人？");
            }
        }, new Executable() {
            @Override
            public void execute() throws Throwable {
                Assertions.assertEquals(3, add(1, 2), "这都能算错？");
            }
        }, new Executable() {
            @Override
            public void execute() throws Throwable {
                Assertions.assertArrayEquals(new int[] {1, 2}, new int[] {1, 2}, "不是同一个数组");
            }
        });
    }

    @Test
    @DisplayName("测试异常断言")
    void testAssertThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int num = 1 / 0;
        }, "代码竟然没问题？");
    }

    @Test
    @DisplayName("测试超时断言")
    void testAssertTimeout() {
        Assertions.assertTimeout(Duration.ofSeconds(3L), new Executable() {
            @Override
            public void execute() throws Throwable {
                Thread.sleep(4000);
            }
        }, "太慢了~");
    }

    @Test
    @DisplayName("测试快速失败")
    void testFail() {
        Assertions.fail("失败了");
    }
}
