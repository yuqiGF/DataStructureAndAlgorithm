package com.yuqiqi.algorithm.sort;

/**
 * 插入排序   每次从未排序区取一个元素  插入到已排序区    ⭐稳定 n^2
 */
public class InsertionSort {
    private static void insertion(int[] a,int low){
        if (low == a.length){ //已经排到了最右边  最后的位置
            return;
        }
        int t = a[low]; //待排序部分最左边的位置的数值  也就是这轮要拍的数⭐
        int i = low - 1; //已排序部分的最大值所在的位置
        //⭐给t找位置！！
        while(i >= 0 && a[i] > t){  //从右往左找 没找到的话不断循环
            a[i + 1] = a[i]; //把a[i]的值向右移动一位，来给接下来要插入的东西腾位置
            i --;
        }
        //退出循环就是找到了
        if (i + 1 != low){    //微小的优化  如果i + 1 = low 的话就没必要赋值了  原本就是有序的
            a[i + 1] = t; //把要排的元素插入到找到的位置
        }
        //递归
        insertion(a,low + 1);  //递归的时候输入当前待排序位置的下一个位置 进行排序
    }


    /**
     * 循环实现
     */
    private static void insertion2(int[] a){
        for (int i = 1; i < a.length; i++) { //从1开始遍历 （未排序部分）
            int key = a[i]; //记录当前正在排的这个数
            int j = i - 1;//已排序部分的最大索引
            while (j >= 0 && key < a[j]){  //要找第一个比它小的数  以确定自己的位置
                //没找到就整体后移腾出来索引j的位置
                a[j + 1] = a[j];
                j --;
            }
            //总能找到（或者到头了）  空位置也腾了 直接赋值就行了
            a[j + 1] = key;
        }
    }
}
