package com.yuqiqi.leetcode.ds.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 按照频率将数组升序排序
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
 */
public class E1636 {
    //思路： 两次计数排序  ⭐都得使用自定义比较器（捂脸）
    public static int[] frequencySort(int[] nums) {
        //找最大最小值
        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            if (num > max){
                max = num;
            }
            if (num < min){
                min = num;
            }
        }
        System.out.println("min = " + min);
        //直接指定了可以更快
        int[] count = new int[max - min + 1];
        //放入计数数组
        for (int num : nums) {
            count[num - min] ++;
        }
        //⭐用stream流指定比较规则
        int minf = min;
        return Arrays.stream(nums).boxed().sorted((a, b) -> {   //先用流式装箱  然后自定义比较规则 然后装箱
            int af = count[a - minf];
            int bf = count[b - minf];
            if (af < bf) {
                return -1;
            } else if (af > bf) {
                return 1;
            } else {
                return b - a;
            }
        }).mapToInt(Integer::intValue).toArray();
    }



    public static void main(String[] args) {
        int[] test = new int[]{-1,1,-6,4,5,-6,1,4,1};

    }
}
