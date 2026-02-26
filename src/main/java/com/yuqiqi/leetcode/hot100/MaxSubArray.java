package com.yuqiqi.leetcode.hot100;

import java.text.BreakIterator;

/**
 * 最大连续子数组
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        //思路一 前缀和
        int sum = 0;  //前缀和
        int min = 0;  //最小前缀和
        int result = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;  //维护前缀和
            result = Math.max(result , sum - min);
            min = Math.min(min , sum);  //维护最小前缀和
        }
        return result;
    }

    //思路二  动态规划
    public int maxSubArray2(int[] nums){
        int[] res = new int[nums.length];
        res[0] = nums[0];  //dp
        int ans = res[0];  //结果
        for (int i = 1; i < nums.length; i++) {
            res[i] = Math.max(res[i - 1] , 0) + nums[i];  //拼接⭐  和0比
            ans = Math.max(ans , res[i]);
        }
        return ans;
    }
}
