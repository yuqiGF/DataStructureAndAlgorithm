package com.yuqiqi.algorithm.recursion;

/**
 * 递归的方式实现二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        return;
    }

    public static int search(int[] a, int target){
        return binarySearch(a,target,0,a.length - 1);
    }

    private static int binarySearch(int[] a, int target,int i,int j){
        //递归出口
        if (i > j){
            return -1;
        }
        int mid = i + j >>> 1 ;  //右移一位表示除以2
        if (target < a[mid]){
            return binarySearch(a,target,i,mid - 1);
        }else if (a[mid] < target){
            return binarySearch(a,target,mid + 1,j);
        }else {
            return mid;
        }
    }
}
