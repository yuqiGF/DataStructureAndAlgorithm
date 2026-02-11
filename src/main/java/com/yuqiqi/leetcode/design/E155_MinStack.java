package com.yuqiqi.leetcode.design;

import org.checkerframework.checker.units.qual.min;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class E155_MinStack {
    class MinStack {
        //思路 维护一个最小元素的索引数组⭐
        int[] array = new int[30000];
        int[] minIdx = new int[30000];
        int top = 0;  //栈顶 指向下一个要添加的位置

        public MinStack() {

        }

        public void push(int val) {
            array[top] = val;
            //维护最小索引
            if (top == 0){
                minIdx[top] = 0;
            }
            else if (val < array[minIdx[top - 1]]){
                minIdx[top] = top;
            }else {
                minIdx[top] = minIdx[top - 1];
            }
            top ++;
        }

        public void pop() {
            top --;
        }

        public int top() {
            return array[top - 1];
        }

        public int getMin() {
            return array[minIdx[top - 1]];
        }
    }
}
