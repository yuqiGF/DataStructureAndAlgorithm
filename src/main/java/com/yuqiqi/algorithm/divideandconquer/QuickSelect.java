package com.yuqiqi.algorithm.divideandconquer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 分治思想   快速选择算法
 */
public class QuickSelect {
    /*
        求排在第i名的元素  i从0开始 由小到大⭐ 由小到大⭐
     */
    static int quick(int[] array , int left , int right , int i){
        //分区和交换方法和快速排序一模一样
        int p = partition(array, left, right);  //返回基准点元素的索引
        if (p == i){
            return array[p];  //找到了  直接返回
        }
        if (i < p){  //到左边找
            return quick(array,left,p - 1,i);
        }else { //到右边找
            return quick(array,p + 1,right,i);
        }
    }

    private static int partition(int[] a, int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;  //left到right的随机数
        swap(a,left,idx);  //先把最左边的元素替换为随机元素
        int pv = a[left]; //基准点
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && a[j] > pv) {
                j--;  //j找比pv小的  没找到就继续找
            }
            while (i < j && a[i] <= pv) {
                i++;  //i找比pv大的  没找到就继续找
            }
            //找到了
            swap(a, i, j);
        }
        swap(a, i, left); //基准点换回中间
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
