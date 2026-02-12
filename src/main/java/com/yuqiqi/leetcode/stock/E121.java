package com.yuqiqi.leetcode.stock;

/**
 * 股票买卖的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class E121 {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];
        for (int price : prices) {
            max = Math.max(max , price - min);
            min = Math.min(min  , price);
        }
        return max;
    }

    //动态规划
    public int maxProfitDp(int[] prices){
        if (prices.length <= 1){
            return 0;
        }
        int[][] dp = new int[prices.length][2];   //收益 / 持股状态 （0未持有  1已持有）
        dp[0][0] = 0;  //不持股
        dp[0][1] = -prices[0];  //持股
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] ,dp[i - 1][1] + prices[i]);   //不持股状态  可以不买  也可以是卖出的
            dp[i][1] = Math.max(dp[i - 1][1] , - prices[i])  ;  //持股状态  可以接着持股  也可以是买入后持的（从0买入）
        }
        return dp[prices.length - 1][0];
    }
}
