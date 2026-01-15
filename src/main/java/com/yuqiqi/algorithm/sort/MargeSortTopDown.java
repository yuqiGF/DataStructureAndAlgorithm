package com.yuqiqi.algorithm.sort;

/**
 * ⭐归并排序  n log n  稳定
 * -分 每次从中间切一刀 处理一半的数据
 * -治 当数据仅剩一个时可以认为是有序的
 * -合 两个有序的结果进行合并
 */
public class MargeSortTopDown {
    public static void sort(int[] a){
        int[] a2 = new int[a.length];  //辅助数组
        split(a,0,a.length - 1,a2);
    }

    /**
     * 分割
     * @param a 数组
     * @param left 左边界
     * @param right 右边界
     * @param a2 辅助数组
     */
    private static void split(int[] a, int left , int right , int[] a2){
        //治
        if (left == right){
            return;  //分到最小了
        }
        //分
        int m = (left + right) >>> 1;
        split(a,left,m,a2);
        split(a,m + 1, right,a2);
        //合
        merge(a ,left ,m ,m + 1 ,right , a2 );
        System.arraycopy(a2,left,a,left,right - left + 1);  //⭐把辅助数组合并到原数组   只复制当前合并的范围
    }

    /**
     * 合并   单个数组中两个有序部分的合并  （利用辅助数组和辅助索引）
     * @param a1 原数组
     * @param i 起始
     * @param iEnd 末尾
     * @param j 起始
     * @param jEnd 末尾
     * @param a2 辅助数组
     */
    public static void merge(int[] a1 , int i, int iEnd , int j , int jEnd , int[] a2){
        int k = i;  //辅助数组的索引⭐
        while(i <= iEnd && j <= jEnd){
            if (a1[i] < a1[j]){
                a2[k] = a1[i];
                i ++;
            }else {
                a2[k] = a1[j];
                j ++;
            }
            k ++;
        }
        //处理剩下的元素
        if (i > iEnd){  //i部分结束了 把j部分剩下的全部移动到a2
            System.arraycopy(a1,j,a2,k,jEnd - j + 1);
        }
        if (j > jEnd){
            System.arraycopy(a1,i,a2,k,iEnd - i + 1);
        }
    }
}
