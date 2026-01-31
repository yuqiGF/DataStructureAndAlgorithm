package com.yuqiqi.leetcode.ds.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 最大间距   给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 */
public class E164 {
    //思路：直接java排序  然后比较     ⭐还有一种方法是桶排序  找每个桶和桶间的最大差值
    public static int maximumGap(int[] nums) {
        if (nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            int gap = nums[i] - nums[i - 1];
            if (gap > max){
                max = gap;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{1000000000,99999999,9999999,999999,99999,9999,999,99,9}));
    }
}
