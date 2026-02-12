package com.yuqiqi.leetcode.stock;

/**
 * 买卖股票的最佳时机Ⅲ
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class E123 {
    //额外记录一个买卖次数的维度
    public int maxProfit(int[] prices) {
        /*
            ⭐第一次买  不依赖之前的价格 以当日价格买入
            ⭐第一次卖  依赖第一次买 + 当日价格
            ⭐第二次买  依赖昨天第一次卖 - 当日价格
            ⭐第二次卖  依赖第二次买 + 当日价格
         */
        int n = prices.length;
        int[][][] dp = new int[n][2][2];  //天数/状态/次数 -> 利润
        dp[0][0][0] = 0;  //第一天 不持股
        dp[0][1][0] = -prices[0];  //第一天 持股
        dp[0][0][1] = 0;  //第二天 不持股
        dp[0][1][1] = -prices[0];  //第二天 持股

        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0] , dp[i - 1][1][0] + prices[i]);  //第一天  不持股
            dp[i][1][0] = Math.max(dp[i - 1][1][0] , - prices[i]);  //第一填  持股  ⭐重点者这里  第一次买不用关心上次的状态
            dp[i][0][1] = Math.max(dp[i - 1][0][1] , dp[i - 1][1][1] + prices[i]);  //第二天的初始状态变成第一天的结果
            dp[i][1][1] = Math.max(dp[i - 1][1][1] , dp[i - 1][0][0] - prices[i]);  //第一天不持股的状态买入
        }
        return dp[n - 1][0][1];
    }

    //优化版   ⭐状态机解法  几个变量滚动更新  （买入 卖出⭐）
    public int maxProfit2(int[] prices){
        //以买入和卖出区分状态
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1 , - price);
            sell1 = Math.max(sell1 , buy1 + price);
            buy2 = Math.max(buy2 , sell1 - price);
            sell2 = Math.max(sell2 , buy2 + price);
        }
        return sell2;
    }
}
