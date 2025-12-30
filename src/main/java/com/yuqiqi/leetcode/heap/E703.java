package com.yuqiqi.leetcode.heap;

import com.yuqiqi.dataStructure.heap.MinHeap;

/**
 * 从数据流中返回第k大的元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。  ⭐这里nums是初始的数据流
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 */
public class E703 {
    //声明小顶堆
    private MinHeap heap;
    /**
     * 思路  小顶堆  不断往里面添加元素， 比堆顶小的忽略 比堆顶大的替换堆顶元素 然后堆顶下潜
     * @param k 第k个元素
     * @param nums
     */
    public E703(int k, int[] nums) {   //⭐这个方法就是一个初始化  往里面传一个nums数组，这个数组里的东西也要逐个遍历，调用add方法进堆
        heap = new MinHeap(k); //初始化的时候建立小顶堆
        for (int num : nums) {
            add(num);
        }
    }
    //后面要不断得调用这方法，来模拟流的实现
    public int add(int val) {
        //堆没有满的时候往里面添加元素  而不是替换堆顶
        if (heap.size < heap.array.length){
            heap.offer(val);
        }else{
            if (heap.peek() < val){
                heap.replace(val); //替换堆顶元素
                heap.down(0);
            }
        }
        return heap.peek();
    }
}
