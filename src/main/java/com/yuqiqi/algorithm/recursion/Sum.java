package com.yuqiqi.algorithm.recursion;

/**
 * 1-n求和   ⭐爆栈问题
 * 尾调用：函数最后return的是一个单独的函数  其他的东西都执行完了，只和这一个函数有关，和别的没关系了
 * 尾递归；最后调用自己
 * 一些语言 如C++和scala会对尾调用做优化 使它变成线性执行的  就会解决爆栈问题。
 */
public class Sum {

    //递归实现
    public static int sum(int n){
        if (n == 1){
            return 1;
        }
        return sum(n - 1) + n;  //必须递到最深处才能归  太多了就会爆栈
    }
    //循环实现  返璞归真  解决爆栈
    public static long sumFor(int n){
        long sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(sum(100000));  //stackOverflow 栈内存溢出
        System.out.println(sumFor(100000));
    }
}
