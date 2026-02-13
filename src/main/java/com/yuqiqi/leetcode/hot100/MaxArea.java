package com.yuqiqi.leetcode.hot100;

public class MaxArea {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j){
            max = Math.max(max , Math.min(height[i] , height[j]) * (j - i));
            if (height[i] < height[j]){
                i ++;   //左更小  右减减
            }else {
                j --;
            }
        }
        return max;
    }
}
