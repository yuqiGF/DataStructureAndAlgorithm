package com.yuqiqi.leetcode.heap;

import com.yuqiqi.dataStructure.heap.MinHeap;

/**
 * 数组中的第k个最大元素   给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * ⭐解法：先将数组中的前k个元素放入小顶堆，然后剩下的元素逐个和堆顶比较 如果比堆顶小，直接掠过，如果比堆顶大，替换堆顶，然后下潜
 * 最后取堆顶即可  堆顶比下面的几个都小 就是第k小的
 */
public class E215 {
    public int findKthLargest(int[] nums, int k) {
        //建堆 大小为k
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]); //先放入k个
        }
        //剩下的逐个和堆顶比较
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()){
                heap.replace(nums[i]); //替换堆顶
                heap.down(0); //堆顶下潜
            }
        }
        return heap.peek();  //堆顶比下面的几个都小 就是第k小的
    }
}
