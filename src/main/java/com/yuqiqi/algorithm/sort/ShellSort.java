package com.yuqiqi.algorithm.sort;

/**
 * 希尔排序   简单的说就是分组实现插入排序   每组元素之间的间隔成为gap   每轮排序后gap逐渐缩小  直至gap为1完成排序
 * ⭐其实就是对插入排序的一个优化  让插入排序的交换次数变少了（gap为1时就和插入排序完全一样）
 * n log n
 */
public class ShellSort {
    public static void sort(int[] a){
        //生成间隙  每次都为原来的1/2 直到1  （除法可以优化为右移一位）
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            //插入排序 （⭐将原本是1的地方全部改为gap）
            for (int low = gap; low < a.length; low++) {
                int t = a[low];
                int i = low - gap;
                while(i >= 0 && t < a[i]){  //向左移腾位置
                    a[i + gap] = a[i];
                    i -= gap;
                }
                if (i != low - gap){
                    a[i + gap] = t;  //插入空位  等于就不用动了
                }
            }
        }
    }
}
