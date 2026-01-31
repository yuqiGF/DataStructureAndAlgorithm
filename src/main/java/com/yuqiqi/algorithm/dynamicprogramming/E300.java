package com.yuqiqi.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 最长递增子序列  ⭐ 动态规划
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class E300 {
    //思路 动态规划  ⭐一维数组即可
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 1; //记录最大值（从1开始）
        Arrays.fill(dp,1);  //初始都是1 和自己构成子序列
        for (int i = 1; i < nums.length; i++) {  //外层循环从第二个字符开始
            for (int j = 0; j < i; j++) {  //后面的字符向前找 也要一个循环
                if (nums[i] > nums[j]){  //⭐满足升序条件的
                    dp[i] = Math.max(dp[j] + 1,dp[i]);  //本身是可以不变的
                    max = Math.max(max,dp[i]);
                }
            }
        }
        return max;
    }
}
