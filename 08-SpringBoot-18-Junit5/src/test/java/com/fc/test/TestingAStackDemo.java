package com.fc.test;

import org.junit.jupiter.api.*;

import java.util.EmptyStackException;
import java.util.Stack;

// 内层能够驱动外层，外层是不能驱动内层，
// 内层的操作对外层是不可见的
// 官方文档案例，一个栈的测试类
@DisplayName("A stack")
class TestingAStackDemo {
    // 声明一个栈
    Stack<Object> stack;

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {
        // 每次执行方法前都会执行此方法
        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            Assertions.assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            Assertions.assertThrows(EmptyStackException.class, () -> {
                // 从栈中弹出一个元素，弹最接近栈顶的元素
                stack.pop();
            });
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            Assertions.assertThrows(EmptyStackException.class, () -> {
                // 从栈顶位置获取一个元素，栈中的元素不会被删除
                stack.peek();
            });
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {
            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                Assertions.assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                Assertions.assertEquals(anElement, stack.pop());
                Assertions.assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                Assertions.assertEquals(anElement, stack.peek());

                Assertions.assertFalse(stack.isEmpty());
            }
        }
    }
}