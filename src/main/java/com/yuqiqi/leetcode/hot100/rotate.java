package com.yuqiqi.leetcode.hot100;

/**
 * 轮转数组   ⭐反转整个数组  然后反转前k个  然后反转后n-k个
 */
public class rotate {
    public void rotate(int[] nums, int k) {
        // //新数组索引替换
        // int[] res = new int[nums.length];
        // int l = 0;
        // for(int i = 0 ; i < nums.length ; i ++){
        //     if(l < k % nums.length){
        //         res[l ++] = nums[nums.length - 1 + l - k % nums.length];
        //     }
        //     else{
        //         res[l] = nums[l - k % nums.length];
        //         l ++;
        //     }
        // }
        // for(int i = 0 ; i < nums.length ; i ++){
        //     nums[i] = res[i];
        // }

        k = k %nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k - 1);
        reverse(nums,k,nums.length - 1);
    }
    // 数组拆分反转
    private void reverse(int[] nums, int i, int j) {
        while(i < j){
            int temp = nums[i];
            nums[i ++] = nums[j];
            nums[j --] = temp;
        }
    }
}
