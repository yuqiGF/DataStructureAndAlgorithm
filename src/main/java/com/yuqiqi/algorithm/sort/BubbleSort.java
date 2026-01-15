package com.yuqiqi.algorithm.sort;

/**
 * 冒泡排序   每轮不断的比较相邻的元素  如果是逆序的 就交换位置
 * 下一轮冒泡可以调整未排序的右边界  减少不必要的比较
 * 稳定  n^2
 */
public class BubbleSort {
    /**
     * 递归实现
     * @param a 未排序数组
     * @param j 未排序区的右边界
     */
    public static void bobble(int[] a , int j){
        if (j == 0){
            return;
        }
        int x = 0;  //记录最后一次交换的位置 减少不必要的比较
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i+1]){
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                x = i;
            }
        }
        bobble(a,x);  //递归  未排序区缩小
    }

    /**
     * 非递归实现
     * @param a 待排序数组
     */
    public static void bobble(int[] a){
        int j = a.length - 1;  //初始化未排序数组的右边界
        do {
            int x = 0;  //记录最后一次交换的位置 减少不必要的比较
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    x = i;
                }
            }
            j = x;
        } while (j != 0);
    }
}
