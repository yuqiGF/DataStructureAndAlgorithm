package com.yuqiqi.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * ⭐01背包问题  物品不能分割   不能用贪心算法  得用动态规划
 *  * n个物品都是固体，有重量和价值
 *  * 要取走不超过10克的物品
 *  * 每次可以不拿或者全拿
 */
public class Knapsack {
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

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(0, 4, 24),
                new Item(1, 8, 160),
                new Item(2, 2, 4000),
                new Item(3, 6, 108),
                new Item(4, 1, 4000),
        };
        System.out.println(select(items, 10)); //假设最大能装10kg
    }

    public static int select(Item[] items , int total){
        //⭐可降维  手动遍历物品数组  背包重量从右向左遍历
         int[][] dp = new int[items.length][total + 1];  //行是物品种类   列是背包容量   dp代表当前容量下的最大利益
        /**
         * 递推关系
         * if 装不下 {
         *           dp[i][j] = dp[i-1][j]
         * }else{
         *           dp[i][j] = max(dp[i-1][j],item.value + dp[i - 1][j - item.weight])  本身的价值和新放入的价值求和取一个最大值
         *           ⭐这里本身这个物品拿完了
         *       }
         */
        //处理第一行数据   填满
        Item item0 = items[0];
        for (int j = 0; j < total + 1; j++) {
            if (j >= item0.weight){  //放得下
                dp[0][j] = item0.value;
            }else {  //放不下
                dp[0][j] = 0;
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        //处理剩下的行（物品）
        for (int i = 1; i < dp.length; i++) {  //从第二行开始
            for (int j = 0; j < total + 1; j++) {
                if (j >= items[i].weight){  //装得下第j个物品
                    dp[i][j] = Math.max(dp[i - 1][j],items[i].value + dp[i - 1][j - items[i].weight]);
                }else {  //装得下第j个物品  继承上一行
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[items.length - 1][total];  //返回最右下角的值
    }
}
