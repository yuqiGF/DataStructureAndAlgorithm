package com.yuqiqi.algorithm.recursion;

import java.util.Arrays;

/**
 * 斐波那契数列  ⭐多路递归 每次递归里面多次调用了自己
 */
public class Fibonacci {

//⭐ 求调用了几次的话有一个公式： 2*f(n+1) - 1   和本身斐波那契值有关   可得时间复杂度为Θ 1.618^n  黄金分割比的n次  （紧界）
    /** 斐波那契数列递归函数
     * 地推关系 f(n) = f(n-1)+f(n-2)   n=1是 f=1  n=0时 f=0   可以理解为后续遍历   先计算两边  再计算中间
     * @param n 第几项
     * @return 结果
     */
    public static int f(int n){
        //结束条件
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        //多路递归
        return f(n - 1)+f(n - 2);
    }

    /**
     * 变体  兔子问题  一个兔子每次生一只小兔子，每只小兔子要一个月成熟  兔子每个月生一次  请问n个月后一共多少只兔子
     * 解法： 第n个月的兔子数 = n-1月的兔子数 + n-1月能生孩子的兔子数（即第n-2个月的兔子数）
     */

    /**
     * 变体  青蛙爬楼梯   每次可以跳一个或者两个    把最后一次跳的情况分开  跳一个和跳两个   递归结束条件就是只剩1个台阶或者两个台阶
     * 比如说5级只能由4级或者3级跳上去  由此拆分  直到只剩1 2
     */

    /**
     * 记忆化改进 剪枝  每次传入的时候带上一个记录数组
     */
    public static int fibonacci(int n ,int[] cache){
        if (cache[n] != -1){  //已经有值了  直接返回就行
            return cache[n]; //计算完了
        }
        //将每次的计算结果存入数组中   则数组中的最后一个数就是要求的结果了
        cache[n] = fibonacci(n - 1,cache) + fibonacci( n - 2,cache);
        return cache[n]; //填入当前计算的值
    }

    /**
     * 外部使用接口
     * @param n
     * @return
     */
    public static int result(int n){
        int[] cache = new int[n+1];  //数字的索引位置存数列第索引位置的值  第0项到第n项 一共n+1个
        Arrays.fill(cache,-1);  //全部填充为-1
        //前两项已知
        cache[0] = 0;
        cache[1] = 1;
        return fibonacci(n,cache);
    }
}
