package com.yuqiqi.algorithm.dynamicprogramming;

import java.sql.Array;

/**
 * 最长字串  ⭐动态规划
 * 思路：把两个字符串拆成俩字符数组，然后放在二维表格里   某位置的左上角有东西 就说明它是连着的
 * if 遇到相等的
 *      dp[i][j] = d[i - 1][j - 1] + 1;
 * else
 *      dp[i][j] = 0
 */
public class LCSubstring {
    static int lcs(String a , String b){
        int[][] dp = new int[a.length()][b.length()];
        int sum = 0;  //记录总数
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)){
                    if (i == 0 || j == 0){  //行或者列为0的话直接赋值为1  避免索引越界
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    if (dp[i][j] > sum){
                        sum = dp[i][j];
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return sum;
    }

    static int lcs2(String a , String b){
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        int sum = 0;  //记录总数
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > sum){
                        sum = dp[i][j];
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(lcs2("yuqiqi", "yuqi"));
        String a = "yuqiqi";
    }

}
