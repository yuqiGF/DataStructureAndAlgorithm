package com.yuqiqi.leetcode;

/**
 * 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 */
public class E11 {
    public static int maxArea(int[] height) {
        //思路：双指针  初始指向两边  ⭐每次改变较短的那边的挡板 再和初始的容积对比 取较大值
        int i = 0;
        int j = height.length - 1;
        int max = Math.min(height[i] , height[j]) * (j - i);
        while (i < j){
            if (height[i] < height[j]){
                i ++;
            }else{
                j --;
            }
            max = Math.max(max , Math.min(height[i] , height[j]) * (j - i));
        }
        return max;
    }

    public static void main(String[] args) {
        maxArea(new int[]{1,1});
    }
}
