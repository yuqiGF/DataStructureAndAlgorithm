package com.yuqiqi.algorithm.recursion;

/**
 * 阶乘
 */
public class Factorial {
    public static int f(int n){
        if (n == 1){
            return 1;  //结束条件
        }
        return n * f(n - 1);  //递推关系
    }

    public static void main(String[] args) {
        System.out.println(f(5));
    }
}
