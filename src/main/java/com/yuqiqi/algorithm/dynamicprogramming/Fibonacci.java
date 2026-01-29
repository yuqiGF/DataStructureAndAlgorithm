package com.yuqiqi.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 斐波那契数列  动态规划⭐
 */
public class Fibonacci {
    /*
        要点1：从已知的子问题可以推到出当前问题的解
              推导过程可以表示为一个数学公式
        要点2：用一维数组或者二维数组来保存之前的计算结果
     */
    public static int fibonacci(int n){
        int[] dp = new int[n + 1];  //数组大小为项数 + 1  用来缓存之前的结果
        //赋初始值
        dp[0] = 0;
        //第0项
        if (n == 0){
            return 0;
        }
        dp[1] = 1;
        //第1项
        if (n == 1){
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    /**
     * 降维处理   二维可以降维一维度  一维可以降维为几个变量  ⭐妙啊！！！！
     * @param n
     * @return
     */
    public static int fibonacci2(int n){
        int a = 0;
        int b = 1;
        //第0项
        if (n == 0){
            return 0;
        }
        //第1项
        if (n == 1){
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            int c = a + b;  //计算结果
            //计算完之后a，b，c分别向前移动
            a = b;
            b = c;
        }
        return b;  //最后算完后终止循环时 b和c是相等的
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(5));
    }
}
