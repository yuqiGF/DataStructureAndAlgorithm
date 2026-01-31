package com.yuqiqi.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 零钱兑换问题      类似完全背包问题⭐   用贪心算法不能解决
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
public class E322 {
    public static int coinChange(int[] coins, int amount){
        int[][] dp = new int[coins.length][amount + 1];  //动态规划数组
        if (amount == 0){
            return 0;
        }
        //处理第一行
        for (int i = 1; i < amount + 1; i++) {   //每行的第一个始终为0
            int coin = coins[0]; //第一行的硬币金额
            if (i >= coin){
                dp[0][i] = dp[0][i - coin] + 1;  //硬币数量+1
            }else {
                dp[0][i] = amount + 1;   //后续要取较小的值 所以不能设置为0  ⭐选择总数 + 1作为最大值
            }
        }
        //从第二行硬币开始继续处理
        for (int i = 1; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coin){  //放得下
                    dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - coin] + 1);  //需要最少硬币数
                }else {  //放不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        //拿最后一个值判断
        return dp[coins.length - 1][amount] > amount ? -1 : dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1}, 1));
    }
}
