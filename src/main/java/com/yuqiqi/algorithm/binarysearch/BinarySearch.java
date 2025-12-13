package com.yuqiqi.algorithm.binarysearch;

public class BinarySearch {
    /**
     * 二分查找基础版  （⭐记得±1）
     */
    public static int binarySearchBasic(int[] a,int target){
        int i = 0, j = a.length - 1; //设置指针和初值
        while(i <= j){
            int m = (i + j) >>> 1; //取中间值并向下取整   ⭐这里用无符号右移运算符，代表除以2，防止符号位溢出   给他直接移回来
            if (a[m] < target){ //中间值在目标左边   ⭐注意是值 不是索引
                i = m + 1; //左指针指向中间值+1的位置
            } else if (target < a[m]) {
                j = m - 1;
            }else { //找到了
                return m;
            }
        }
        //没找到返回-1
        return -1;
    }

    /**
     * 二分查找 改动版
     */
    public static int binarySearchAlternative(int[] a , int target){
        int i = 0, j = a.length;  //第一处
        while(i < j){  //第二处
            int m = (i + j) >>> 1; //取中间值并向下取整   ⭐这里用无符号右移运算符，代表除以2，防止符号位溢出   给他直接移回来
            if (a[m] < target){ //中间值在目标左边   ⭐注意是值 不是索引
                i = m + 1; //左指针指向中间值+1的位置
            } else if (target < a[m]) {
                j = m;  //第三处
            }else { //找到了
                return m;
            }
        }
        //没找到返回-1
        return -1;
    }
    /**
     * 平衡二分
     */
    public static int binarySearchBalance(int[] a,int target){
        int i = 0, j = a.length; //j 不指向数组内的元素
        while(j - i > 1){  //范围内只剩一个元素
            int m = (i + j) >>> 1;
            if (target < a[m]){
                j = m;
            }else {
                i = m;
            }
        }
        if (a[i] == target){
            return i;
        }else {
            return -1;
        }
    }

    /**
     * 左侧重复元素查找
     */
    public static int binarySearchLeftmost1(int[] a, int target){
        int i = 0, j = a.length - 1; //设置指针和初值
        int candidate = -1; //候选位置
        while(i <= j){
            int m = (i + j) >>> 1;
            if (a[m] < target){
                i = m + 1;
            } else if (target < a[m]) {
                j = m - 1;
            }else { //找到了
                //⭐ 记录候选位置
                candidate = m;  //继续缩小边界   找最左索引，所以缩小右边界
                j = m - 1;  //直到循环结束退出
            }
        }
        //没找到返回-1
        return -1;
    }
}
