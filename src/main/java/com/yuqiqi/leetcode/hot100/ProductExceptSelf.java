package com.yuqiqi.leetcode.hot100;

/**
 * 除了自身以外数组的乘积   （不让用除法）
 */
public class ProductExceptSelf {
    //思路 前缀乘积 * 后缀乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        //前缀积
        int[] pre = new int[n];
        pre[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        //后缀积
        int[] suf = new int[n];
        suf[n - 1] = 1;
        for (int i = n - 2; i >= 0 ; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }
        //结果乘积
        int[] answer = new int[n];
        for (int i = 0; i < nums.length; i++) {
            answer[i] = pre[i] * suf[i];
        }
        return answer;
    }
}
