package com.yuqiqi.leetcode.stock;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机Ⅳ
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class E188 {
    public int maxProfit(int k, int[] prices) {
        /*
        ⭐状态机DP
            ⭐第一次买  不依赖之前的价格 以当日价格买入
            ⭐第一次卖  依赖第一次买 + 当日价格
            ⭐第二次买  依赖昨天第一次卖 - 当日价格
            ⭐第二次卖  依赖第二次买 + 当日价格
            ………………
            ⭐⭐第k次买 = k - 1次卖 - 当日价格
            ⭐⭐第k次卖 = k 次买 + 当日价格
         */
        int[] buy = new int[k];  //第k次买入
        int[] sell = new int[k];  //第k次卖出
        Arrays.fill(buy , Integer.MIN_VALUE);
        Arrays.fill(sell , 0);

        for (int price : prices) {
            //第一次买卖
            buy[0] = Math.max(buy[0] , -price);
            sell[0] = Math.max(sell[0] , buy[0] + price);
            //后续几次买卖
            for (int i = 1; i < k; i++) {  //内层循环第几次买卖
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i] , buy[i] + price);
            }
        }
        return sell[k - 1];
    }
}
