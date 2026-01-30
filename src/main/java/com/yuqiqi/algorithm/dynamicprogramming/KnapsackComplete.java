package com.yuqiqi.algorithm.dynamicprogramming;

/**
 * ⭐完全背包问题  （每件物品都有无限多）   动态规划⭐
 */
public class KnapsackComplete {
    static class Item {
        int index;
        int weight;  //物品重量
        int value;  //物品价格

        public Item(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "index=" + index +
                    ", weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }
    /**
     * 递推关系
     * if 装不下 {
     *           dp[i][j] = dp[i-1][j]
     * }else{
     *           dp[i][j] = max(dp[i-1][j],item.value + dp[i][j - item.weight])  本身的价值和新放入的价值求和取一个最大值
     *           ⭐这里本身这个物品拿不完                      ↑⭐  ↑去完之后剩下的空间容量
     *       }
     */
    private static int select(Item[] items , int total){
        int[][] dp = new int[items.length][total + 1];
        //初始化第一行
        Item item0 = items[0];
        for (int j = 0; j < total + 1; j++) {
            if (j >= item0.weight){  //放得下
                dp[0][j] = dp[0][j - item0.weight] + item0.value;
            }else {
                dp[0][j] = 0;
            }
        }
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j < total + 1; j++) {
                Item item = items[i];
                if (j >= item.weight){ //放得下
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j-item.weight] + item.value);  //可以选择本行重复的东西
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[items.length - 1][total];
    }
}
