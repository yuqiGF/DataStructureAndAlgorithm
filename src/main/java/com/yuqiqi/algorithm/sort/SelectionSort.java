package com.yuqiqi.algorithm.sort;

/**
 * 选择排序   每一轮选择选出最大或者最小的元素 交换到合适的位置
 */
public class SelectionSort {
    /**
     * 选择排序
     * @param a 待排序元素
     */
    public void sort(int[] a){
        //选择轮数  a.length - 1
        //每次交换的索引位置是初始值 - 1  每轮递减
        for (int right = a.length - 1; right > 0; right--) {
            int max = right;  //最大值的索引
            for (int i = 0; i < right - 1; i++) {
                if (a[i] > a[max]){
                    max = i;  //找出最大元素的索引位置
                }
            }
            if (max != right){
                swap(a,max,right);  //最大值和最右边交换
            }
        }
    }

    private static void swap(int[] a , int i , int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
