package com.yuqiqi.algorithm.sort;


/**
 * 快速排序-单边循环快排（罗穆托分区方案）        确定好一个位置  左边的比它小  右边的比它大，然后对每一边递归操作
 * 1、选择最右边的元素作为基准点  ij指针初始都在最左边
 * 2、j指针用于寻找比基准点小的元素，一旦找到就与i进行交换
 * 3、i指针指向大于基准点的元素的左边界，也是每次交换的目标索引 （没找到比基准点大的元素时和j一起移动  找到了的话就停止 或者等待j交换）
 * 4、最后基准点与i交换，i即为基准点元素的位置  固定不动
 */
public class QuickSortLomuto {
    public static void sort(int[] a){
        quick(a,0,a.length - 1);
    }

    private static void quick(int[] a , int left , int right){
        if (left >= right){
            return;  //分到最小时停止
        }
        //分区  p代表基准点元素的位置
        int p = partition(a,left,right);
        quick(a,left,p - 1);
        quick(a,p + 1,right);
    }

    /**
     * 分区方法
     * @param a 初始数组
     * @param left 左边界
     * @param right 有边界
     * @return 基准点元素的索引位置
     */
    private static int partition(int[] a, int left, int right) {
        int pv = a[right]; //基准点
        int i = left;
        int j = left;
        while(j < right){
            if (a[j] < pv){  //找到比基准点小的了
                if (i != j){
                    swap(a,i,j);  //交换
                }
                i ++;  //⭐没有找到比基准点大的元素  i增加
            }
            j ++;
        }
        swap(a,i,right);  //把基准点元素交换到中间
        return i; //基准点元素所在位置
    }

    private static void swap(int[] a , int i , int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
