package com.yuqiqi.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 零钱兑换二   ⭐动态规划
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 */
public class E518 {
    /*
        递推关系：
        if放得下  dp[i][j] = dp[i - 1][j]+dp[i][j - coin]
     */
    public static int change(int amount, int[] coins){
        int[][] dp = new int[coins.length][amount + 1];  //类似于完全背包问题
        //填充第一列
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        //填充第一行
        for (int i = 1; i < amount + 1; i++) {
            int coin = coins[0];
            if (i >= coin){  //放得下
                dp[0][i] = dp[0][i - coin];   //⭐ 和前面的组合数相同
            }else {
                dp[0][i] = 0;
            }
        }
        //处理剩下的行
        for (int i = 1; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 1; j < amount + 1; j++) {  //第一列都是1 不用处理了
                if (j >= coin){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin]; //上一行的组合数+本行剩余金额的组合数
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[coins.length - 1][amount];
    }

    //⭐降维
    public static int change2(int amount, int[] coins){
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(change2(5, new int[]{1, 2, 5}));
    }
}
