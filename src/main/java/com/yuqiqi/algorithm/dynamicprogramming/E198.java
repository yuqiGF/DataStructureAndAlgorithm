package com.yuqiqi.algorithm.dynamicprogramming;

/**
 * 打家劫舍  ⭐动态规划
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class E198 {
    public int rob(int[] nums) {
        if (nums == null){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        //⭐相邻的不能偷
        int[] dp = new int[nums.length];
        //初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0] , nums[1]);  //⭐从第二个数开始就要取最大值了     遇到第二个我可以选择不偷  直接去偷第一个
        for (int i = 2; i < nums.length; i++) {  //第i个房间要不要偷
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);  //取上一个或者偷
        }
        return dp[nums.length - 1];
    }
}
