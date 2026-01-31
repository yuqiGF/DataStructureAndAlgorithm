package com.yuqiqi.algorithm.dynamicprogramming;

/**
 * 钢条切割问题  动态规划⭐  与完全背包问题基本一样
 * 不同长度的钢条价格不同
 * 长度：0 1 2 3 4 5  6  7  8  9  10
 * 价格：0 1 5 8 9 10 17 17 20 24 30
 * values数组的索引代表当前钢筋的长度（重量）  值代表价格   给定总长度（重量）
 */
public class CutRod {
    /**
     * 动态规划
     * @param values 不同长度的钢条对应的价值
     * @param n 钢条总长度
     * @return 最大的价值
     */
    static int cut(int[] values , int n){
        int[][] dp = new int[values.length][n + 1];
        for (int i = 1; i < values.length; i++) {  //索引代表物品重量 （即钢条长度）
            for (int j = 1; j < n + 1; j++) {
                if (j >= i){  //放得下
                    dp[i][j] = Math.max(dp[i - 1][j], values[i] + dp[i][j - i]);
                }else {  //放不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[values.length - 1][n];
    }

    public static void main(String[] args) {
        System.out.println(cut(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 10));
    }
}
