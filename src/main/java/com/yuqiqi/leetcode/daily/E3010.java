package com.yuqiqi.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 将数组分为最小代价的子数组
 * 给你一个长度为 n 的整数数组 nums 。
 * 一个数组的 代价 是它的 第一个 元素。比方说，[1,2,3] 的代价是 1 ，[3,4,1] 的代价是 3 。
 * 你需要将 nums 分成 3 个 连续且没有交集 的子数组。
 * 请你返回这些子数组的 最小 代价 总和
 */
public class E3010 {
    public int minimumCost(int[] nums) {
        //思路 就是找最小的三个元素然后加起来
        //第一个数要特殊处理
        int a = nums[0];
        //小顶堆找最小的俩即可
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 1 ; i < nums.length ; i ++) {
            heap.offer(nums[i]);
        }
        int res = a;
        for (int i = 0; i < 2; i++) {
            res += heap.poll();
        }
        return res;
    }

    //另一思路  就是找最大的俩
    public int minimumCost2(int[] nums){
        int a = nums[0];
        int temp = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                temp = Math.min(temp, nums[i] + nums[j] + a);
            }
        }
        return temp;
    }
}
