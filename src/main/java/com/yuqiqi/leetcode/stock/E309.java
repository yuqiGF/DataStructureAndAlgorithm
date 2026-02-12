package com.yuqiqi.leetcode.stock;

/**
 * 买卖股票的最佳时机 含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * ⭐卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class E309 {
    public int maxProfit(int[] prices) {
        //动态规划
        int n = prices.length;
        int[][] dp = new int[n][2];
        if (n < 2){
            return 0;
        }
        dp[0][0] = 0;  //持股
        dp[0][1] = -prices[0];  //不持股
        dp[1][0] = Math.max(dp[0][0] , dp[0][1] + prices[1]);  //⭐⭐加两个状态即可
        dp[1][1] = Math.max(dp[0][1] , dp[0][0] - prices[1]);
        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] , dp[i - 1][1] + prices[i]);  //不持股状态
            dp[i][1] = Math.max(dp[i - 1][1] , dp[i - 2][0] - prices[i]);  //持股状态  ⭐卖出看的是前两天的状态
        }
        return dp[n - 1][0];
    }
}
