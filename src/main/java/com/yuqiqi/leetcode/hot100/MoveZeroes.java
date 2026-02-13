package com.yuqiqi.leetcode.hot100;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        //思路  原地栈  把nums当作栈
        int i = 0;
        for (int num : nums) {
            if (num != 0){
                nums[i ++] = num;
            }
        }
        for (int j = i; j < nums.length; j++) {
            nums[j] = 0;  //补0
        }
    }

    //思路二 指针交换
    public void moveZeroes2(int[] nums){
        int i = 0;  //记录位置
        for (int j = 0 ; j < nums.length ; j ++) {
            if (nums[j] != 0){  //不为零时往前换
                swap(nums , i , j);
                i ++;
            }
        }
    }

    public void swap(int[] nums , int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
