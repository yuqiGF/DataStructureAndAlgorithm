package com.yuqiqi.algorithm.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void f() {
        System.out.println(Fibonacci.f(10));
    }

    @Test
    void testCeil(){
        System.out.println(Math.ceil(-0.5));
        System.out.println(Math.floor(-0.5));
        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
    }
}