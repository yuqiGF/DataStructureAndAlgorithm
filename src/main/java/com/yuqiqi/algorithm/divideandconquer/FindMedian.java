package com.yuqiqi.algorithm.divideandconquer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * ⭐快速选择算法  找中位数
 */
public class FindMedian {

    public static double find(int[] nums){
        if (nums.length % 2 == 1){  //奇数
            //找中间的
            return quick(nums,0,nums.length - 1 , nums.length / 2);
        }else {  //偶数
            //找中间的俩 求平均数
            return (quick(nums, 0 , nums.length - 1 , nums.length / 2) +
                    quick(nums, 0 , nums.length - 1 , nums.length / 2 - 1))/2.0;
        }
    }

    public static void main(String[] args) {
        System.out.println(find(new int[]{1, 2, 3, 4}));
    }

    //快速选择算法选中间位置的元素
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
