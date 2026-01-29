package com.yuqiqi.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
public class E62 {
    //动态规划   ⭐要走到第i行第j列总的走法数 = i-1,j + i,j - 1   全横是1  全列也是1
    //思路：先缩小范围来看  先看1*2的 2*1的  再看2 * 2 的 就可以发现规律  然后动态规划即可
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        //初始化行和列
        for (int i = 0; i <= n; i++) {
            dp[1][i] = 1;
        }
        for (int[] ints : dp) {
            ints[1] = 1;
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(2, 2));
    }
}
