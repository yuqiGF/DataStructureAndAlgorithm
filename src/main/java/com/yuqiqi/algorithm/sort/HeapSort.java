package com.yuqiqi.algorithm.sort;

import com.yuqiqi.dataStructure.heap.MaxHeap;

/**
 * 堆排顺    ⭐建立大顶堆  （线性数组存储非线性的完全二叉树）
 * ⭐不断将堆顶与堆底元素交换，然后堆的大小减一 隔离出最大的那个数，然会堆顶下潜，
 * 不断重复上一步，继续交换，直到堆中元素为1.这样原本的数组就是排好序的了
 */
public class HeapSort {
    public static int[] headSort(int[] array){
        if (array.length <= 1){
            return array;
        }
        MaxHeap maxHeap = new MaxHeap(array); //建堆（数据结构里写过  弗洛伊德建堆算法）
        while (maxHeap.size > 1){
            maxHeap.swap(0,maxHeap.size - 1);
            maxHeap.size --;
            maxHeap.down(0);
        }
        return array;
    }
}
