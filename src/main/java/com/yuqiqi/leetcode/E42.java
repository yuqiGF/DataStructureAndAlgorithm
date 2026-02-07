package com.yuqiqi.leetcode;

import javax.xml.crypto.Data;
import java.util.LinkedList;

/**
 * 接雨水  给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 思路  ⭐单调栈 ⭐
 */
public class E42 {
    public int trap(int[] height) {
        //初始化单调栈
        LinkedList<Data> stack = new LinkedList<>();
        int max = 0;  //容量
        for (int i = 0; i < height.length; i++) {
            Data right = new Data(height[i], i);  //右侧柱子 （即将放入的）
            //放入之前做一个检查  维护⭐单调栈 并且计算此时的雨水容量
            while (!stack.isEmpty() && stack.peek().height < height[i]){
                Data pop = stack.pop();  //被弹出的柱子
                Data left = stack.peek();  //左侧柱子
                if (left != null){
                    //计算水的容量  一个一个的往上填
                    int width = right.i - left.i - 1;  //宽度
                    int h = Math.min(left.height , right.height) - pop.height;  //高度   （左右高度的较小者-中间的）
                    max += width * h;
                }
            }
            //较高的那个元素入栈
            stack.push(right);
        }
        return max;
    }

    static class Data{
        int height; //高度
        int i; //索引

        public Data(int height, int i) {
            this.height = height;
            this.i = i;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "height=" + height +
                    ", i=" + i +
                    '}';
        }
    }

}
