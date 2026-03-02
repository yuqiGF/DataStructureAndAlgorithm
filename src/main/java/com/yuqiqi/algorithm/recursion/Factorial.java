package com.yuqiqi.algorithm.recursion;

import java.math.BigInteger;

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

    public static int f1(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] * i;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(f1(10));
    }
}
