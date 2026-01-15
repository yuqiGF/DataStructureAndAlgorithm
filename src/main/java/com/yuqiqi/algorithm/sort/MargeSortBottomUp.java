package com.yuqiqi.algorithm.sort;

/**
 * 归并排序  自底向上    两个一组排  然后四个一组排………………
 */
public class MargeSortBottomUp {
    public static void sort(int[] a) {
        int n = a.length;
        int[] a2 = new int[n];  //辅助数组
        //width代表有序区间的宽度 取值依次是1 2 4
        for (int width = 1; width < n; width *= 2) {
            //[left，right]分别代表待合并区域的左右边界
            for (int left = 0; left < n; left += width * 2) {  //第一次一个一个走  第二次两个两个走
                int right = Math.min(left + width * 2 - 1, n - 1);  //右边界 （不能超过最右边）
                int m = Math.min(left + width - 1, n - 1); //中间索引  左起点+宽度-1
                merge(a, left, m, m + 1, right, a2);  //合并
            }
            System.arraycopy(a2,0,a,0,n);
        }
    }

    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;  //辅助数组的索引⭐
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        //处理剩下的元素
        if (i > iEnd) {  //i部分结束了 把j部分剩下的全部移动到a2
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }
}
