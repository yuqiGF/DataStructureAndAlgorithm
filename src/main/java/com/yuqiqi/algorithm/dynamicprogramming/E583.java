package com.yuqiqi.algorithm.dynamicprogramming;

/**
 * 两个字符串的删除操作   ⭐思路：和找最长子序列一模一样
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 */
public class E583 {
    public int minDistance(String word1, String word2) {
        //先找最长子序列  参考E1143
        //转化成char数组效率会更高 ⭐
        char[] A = word1.toCharArray();
        char[] B = word2.toCharArray();
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (A[i - 1] == B[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j - 1]);
                }
            }
        }
        //找到最长子序列为dp[][]
        return word1.length() - dp[word1.length()][word2.length()] + word2.length() - dp[word1.length()][word2.length()];
    }
}
