package com.yuqiqi.leetcode.ds.sort;

import java.util.Arrays;

/**
 * 数组的相对排序
 * 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * 注：
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i]  各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class E1122 {
    //计数排序
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        //计数排序
        int max = arr1[0];
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] > max){
                max = arr1[i];
            }
        }
        //放进去了
        int[] count = new int[max + 1];
        for (int j : arr1) {
            count[j]++;
        }
        int k = 0;
        //取出指定好的顺序的元素
        for (int i = 0; i < arr2.length; i++) {
            while (count[arr2[i]] > 0){
                arr1[k ++] = arr2[i];
                count[arr2[i]] --;
            }
        }
        //取出剩下的元素
        for (int i = 0 ; i < count.length ; i ++) {
            while (count[i] > 0){
                arr1[k ++] = i;
                count[i] --;
            }
        }
        return arr1;
    }

    public static void main(String[] args) {
        int [] arr1 =new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int [] arr2 =new int[]{2,1,4,3,9,6};
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }
}
