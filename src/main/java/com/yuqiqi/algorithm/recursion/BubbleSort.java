package com.yuqiqi.algorithm.recursion;

/**
 * 冒泡排序  两次循环  两两相邻比较   O（n^2）
 */
public class BubbleSort {

    /**
     * 标准循环实现
     * @param a
     */
    public static void sort(int[] a ){
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 递归实现
     * @param j 递归的边界  初始时数组长度-1
     */
    public static void sort2(int[] a,int j){
        if (j == 0){
            return;  //范围缩小到了0   就不用再递归了
        }
        int x = 0;  //记录是否发生了交换  发生了交换的话x的值变为i，x后面的元素一定是排好序的
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]){
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                x = i;
            }
        }
        //未排序区域缩小
        sort2(a,x);
    }
}
