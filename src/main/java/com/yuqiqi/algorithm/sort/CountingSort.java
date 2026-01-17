package com.yuqiqi.algorithm.sort;

/**
 * 计数排序
 * ⭐找到最大值  创建一个大小为最大值加一的count数组  count数组的索引对应原始数组的元素，用来记录该元素的出现次数
 * 然后遍历该数组，根据count的索引及出现次数生成排序后的数组
 * ⭐前提： 元素>=0 数值不能过大  不然会有超级大的空间复杂度
 */
public class CountingSort {
    public static void sort(int[] a){
        int max = a[0];  //找最大值
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max){
                max = a[i];
            }
        }
        int[] count = new int[max + 1];  //计数数组

        for (int v : a) {  //v指原始数组的元素
            count[v] ++; //对应索引的元素++
        }
        int k = 0;  //原始数组的索引
        for (int i = 0; i < count.length; i++) {   //遍历count上的每一个值，直到都减到0
            //i代表原始数组的元素值 count[i]代表出现次数
            while (count[i] > 0){
                a[k ++] = i;
                count[i] --; //元素个数减一
            }
        }
    }

    //改进 解决负数索引  将最小值映射到count[0]的位置
    public static void sort2(int[] a){
        int max = a[0];  //找最大值
        int min = a[0];  //找最小值
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max){
                max = a[i];
            }
            if (a[i] < min){
                min = a[i];
            }
        }
        int[] count = new int[max - min + 1];  //计数数组  ⭐注意大小

        for (int v : a) {  //v指原始数组的元素
            count[v - min] ++; //对应索引的元素++  ⭐注意映射 减去最小值才是真实索引的位置
        }
        int k = 0;  //原始数组的索引
        for (int i = 0; i < count.length; i++) {   //遍历count上的每一个值，直到都减到0
            //i加上原始数组的最小值 代表原始数组的元素值 count[i]代表出现次数
            while (count[i] > 0){
                a[k ++] = i + min;
                count[i] --; //元素个数减一
            }
        }
    }

}
