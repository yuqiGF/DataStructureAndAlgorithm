package com.yuqiqi.algorithm.sort;

import java.util.ArrayList;

/**
 * ⭐基数排序 LSD 从低位向高位排   也就是按位排序  先排个位 再排十位 ………… 这样
 */
public class RadixSort {
    /**
     * 基数排序  （低位到高位  当然也可以高位到低位啦）
     * @param a 待排序数组
     * @param length 数组中的元素的长度
     */
    public static void sort(String[] a,int length){
        //准备桶
        ArrayList<String>[] buckets = new ArrayList[128]; //大一点点 好包含更多符号
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(); //初始化每个桶
        }
        //每一位依次排序
        for (int i = length - 1 ; i >= 0;  i --) {
            //拿到每个字符  并且和索引做一个转换  然后往桶里放
            for (String s : a) {
                buckets[s.charAt(i) - '0'].add(s);  //先假设只有三位
            }
            int k = 0;
            //遍历每个桶 放回原数组
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k ++] = s;
                }
                bucket.clear(); //清空桶 完成一轮排序
            }
        }
    }
}
