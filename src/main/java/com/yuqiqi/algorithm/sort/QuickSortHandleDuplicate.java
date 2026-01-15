package com.yuqiqi.algorithm.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 快速排序-高效处理重复元素版
 * 分区规则：i从left+1开始 从左向右找大于等于基准的   j从right开始 从右向左找小于等于基准的  交换后 i++ j--
 * 循环外 j和基准点交换，j即为分区位置    ⭐主要是为了让下一次的基准点在最中间  防止那种都是相同元素的情况导致的只能排一边的极端情况
 */
public class QuickSortHandleDuplicate {
    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;  //分到最小时停止
        }
        //分区  p代表基准点元素的位置
        int p = partition(a, left, right);
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /**
     * 分区方法   ⭐基准带你选择建议选择随机元素  防止极端情况
     *
     * @param a     初始数组
     * @param left  左边界
     * @param right 有边界
     * @return 基准点元素的索引位置
     */
    private static int partition(int[] a, int left, int right) {
        int pv = a[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {  //⭐
            while (i <= j && a[i] < pv) {  //注意这个&& 防止找过头了
                i++;
            }
            while (i <= j && a[j] > pv) {
                j--;
            }
            if (i <= j){  //⭐
                swap(a,i,j);
                i ++;
                j --;
            }
        }
        swap(a,left,j);
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
