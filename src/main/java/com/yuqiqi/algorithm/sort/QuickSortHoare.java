package com.yuqiqi.algorithm.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 快速排序-双边快排   ⭐都是i指向大的 j指向小的
 * -基准点选择最左边
 * -i，j指针分别从左右两边开始遍历
 * -i找到比基准点大⭐的  j找到比基准点小⭐的 时候交换对应位置的元素   直到两指针在中间相遇
 * -最后基准点与i或者j交换，把基准点的位置确定在中间
 */
public class QuickSortHoare {
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
