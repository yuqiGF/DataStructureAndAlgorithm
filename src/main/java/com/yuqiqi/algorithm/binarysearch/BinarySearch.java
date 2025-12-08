package com.yuqiqi.algorithm.binarysearch;

public class BinarySearch {
    /**
     * 二分查找基础版  （⭐记得±1）
     */
    public static int binarySearchBasic(int[] a,int target){
        int i = 0, j = a.length - 1; //设置指针和初值
        while(i <= j){
            int m = (i + j)/2; //取中间值并向下取整
            if (a[m] < target){ //中间值在目标左边   ⭐注意是值 不是索引
                i = m + 1; //左指针指向中间值+1的位置
            } else if (a[m] > target) {
                j = m - 1;
            }else { //找到了
                return m;
            }
        }
        //没找到返回-1
        return -1;
    }
}
