package com.yuqiqi.leetcode.hot100;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 滑动窗口最大值
 */
public class MaxSlidingWindow {
    //思路一  暴力解法  （超时）
    public int[] maxSlidingWindow(int[] nums , int k){
        int[] result = new int[nums.length - k + 1];
        if (k == 1){
            return nums;
        }
        int i = 0;
        while (i <= nums.length - k){
            int max = nums[i];
            for (int j = i; j < k + i; j++) {
                if (nums[j] > max){
                    max = nums[j];
                }
            }
            result[i ++] = max;
        }
        //处理最后一个数
        result[i] = Math.max(nums[nums.length - 1], result[i - 1]);
        return result;
    }

    //思路二  单调递减队列   ⭐存下标
    public static int[] maxSlidingWindow2(int[] nums , int k){
        LinkedList<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        //特殊情况
        if (nums.length < k){
            return new int[]{};
        }
        for (int j = 0; j < nums.length; j++) {
            //出队逻辑  窗口起始位置出去了 就溢出
            while (!deque.isEmpty() && deque.peekFirst()  < j - k + 1){
                deque.pollFirst();  //队首出去
            }
            //队首比自己小的时候出队
            while (!deque.isEmpty() && nums[j] >= nums[deque.peekLast()]){  //取队尾
                deque.pollLast();  //队尾出去
            }
            deque.offerLast(j);
            if (j >= k - 1){
                result[j - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[]{1,3,1,2,0,5}, 3)));
    }


}
