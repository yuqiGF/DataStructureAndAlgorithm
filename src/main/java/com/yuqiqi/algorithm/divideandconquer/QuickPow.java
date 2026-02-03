package com.yuqiqi.algorithm.divideandconquer;

/**
 * 快速幂问题   力扣50题   ⭐分治思想
 */
public class QuickPow {
    static double myPow(double x , int n){
        long a = n;
        if (n < 0){  //处理负数幂
            a = -a;
            x = 1/x;
        }
        if (a == 0){
            return 1;
        }
        if (a == 1){  //递归结束条件   拆到最小了
            return x;
        }
        double y = myPow(x, (int) (a / 2));
        if (a % 2 == 0){  //偶数
            return y * y;
        }else {  //奇数
            return y * y * x;   //额外乘一个x
        }
    }

    public static void main(String[] args) {
        System.out.println(myPow(2, -2147483648));
    }
}
