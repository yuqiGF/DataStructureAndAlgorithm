package com.yuqiqi.algorithm.divideandconquer;

import java.util.concurrent.ThreadLocalRandom;

/**
 *  力扣215 选择数组数第k大的元素   快速选择算法
 */
public class KthLongest {
    public int findKthLargest(int[] nums, int k){
        //由大到小排  需要快速选择算法做一个索引转换
        return quick(nums,0,nums.length-1 , nums.length - k);
    }

    public static int quick(int[] array , int left , int right , int i){
        int p = partition(array, left, right);
        if (p == i){
            return array[p];
        }
        if (i < p){
            return quick(array,left , p - 1, i);
        }else {
            return quick(array,p + 1 , right ,i);
        }
    }

    public static int partition(int[] array , int left , int right){
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(array,idx,left);  //把随机元素换到最左边
        int pv = array[left]; //基准点位置的值
        int i = left;
        int j = right;
        while (i < j){  //保证左边比pv小  右边比pv大
            while (i < j && array[j] > pv){
                j --;
            }
            while (i < j && array[i] <= pv){
                i ++;
            }
            swap(array,i,j);
        }
        //最后把最左边的元素放回中间
        swap(array,left,i);
        return i;  //返回基准点元素索引位置
    }

    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
