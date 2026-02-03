package com.yuqiqi.leetcode.daily;

/**
 * 三段式数组
 *
 */
public class E3637 {
    public static boolean isTrionic(int[] nums) {
        if (nums.length <= 3){
            return false;
        }
        int i = 0;
        int j = 1;
        if (nums[j] <= nums[i]){
            return false;
        }
        while (j < nums.length){
            if (nums[j] < nums[i]){
                break;
            }
            i  ++;
            j ++;
        }
        while (j < nums.length){
            if (nums[j] > nums[i]){
                break;
            }
            i ++;
            j ++;
        }
        if (j == nums.length){
            return false;
        }
        if (j == nums.length - 1 && nums[j] <= nums[i]){
            return false;
        }
        while (j < nums.length){
            if (nums[j] < nums[i]){
                return false;
            }
            i ++;
            j ++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isTrionic(new int[]{1,3,5,4,2,6}));
    }
}
