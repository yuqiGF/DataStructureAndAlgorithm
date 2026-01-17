package com.yuqiqi.algorithm.sort;

import java.util.ArrayList;

/**
 * 桶排序    将数据划分为多个桶  然后每个桶里面排排序
 */
public class BucketSort {
    public static void sort(int[] ages, int range){  //range用来限制每个桶里的元素个数 减少桶的数量
        //找最大值和最小值
        int max = ages[0];  //找最大值
        int min = ages[0];  //找最小值
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] > max){
                max = ages[i];
            }
            if (ages[i] < min){
                min = ages[i];
            }
        }
        ArrayList[] buckets = new ArrayList[(max - min)/range + 1];  //桶 们  用range限制大小
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();  //初始化桶
        }
        //放入数据  假设是年龄
        for(int age : ages){
            buckets[(age - min) / range].addLast(age);  //放入对应的桶
        }
        int k = 0;  //原数组的索引值
        for (ArrayList bucket : buckets) {
            //排序筒内元素  元素较少的话插入排序好一点
            Object[] array = bucket.toArray();
            for (int i = 1; i < array.length; i++) {
                int key = (int)array[i];
                int j = i - 1; //已排序部分的索引
                while ( j >= 0 && (int) array[j] > key){
                    array[j + 1] = array[j];
                    j --;
                }
                array[j + 1] = key;
            }
            //放回原数组
            for (Object v : array) {
                ages[k++] = (int) v;
            }
        }
        //⭐解决数值间相差很小的问题：  桶的数量定为最大值减最小值  放入数据的时候放入当前值-最小值的位置 （这tm不就是计数排序嘛） 然后就用range限制一下桶的大小就行

    }
}
