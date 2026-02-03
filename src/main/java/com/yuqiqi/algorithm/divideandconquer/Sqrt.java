package com.yuqiqi.algorithm.divideandconquer;

/**
 * 求平方根的整数部分   力扣69    ⭐二分的思路
 */
public class Sqrt {
    public static int mySqrt(int x) {
        //思路：二分
        long i = 0;
        long j = x;
        long r = 0;  //记录最大的平方根值
        while (i <= j){
            //找中间值
            long m = i + j >>> 1;
            long mm = m * m;   //防止越界还可以把乘法换成除法
            if (x < mm){
                j = m - 1 ;
            }else if (x > mm){
                i = m + 1;
                r = m;   //⭐记录平方根比规定值小的时候
            }else {
                return (int)m;
            }
        }
        return (int)r;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}
