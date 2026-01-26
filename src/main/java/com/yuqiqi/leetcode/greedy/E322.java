package com.yuqiqi.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
public class E322 {
    static int min = -1;  //需要的最少硬币数量

    public static int coinChange(int[] coins, int amount) {
        rec(0,coins,amount,new AtomicInteger(-1));
        return min;
    }
    /**
     * 暴力递归  （出大问题  时间复杂度爆炸 而且还是错的）
     */
    public static void rec(int index , int[] coins , int remains , AtomicInteger count){
        count.incrementAndGet();  //count自增  进入递归的时候增大 用于计数
        //剩余数量大于0
        if (remains > 0){
            for (int i = index; i < coins.length; i++) {
                rec(i,coins,remains - coins[i],count);
            }
        }
        //剩余数量等于0  找到了
        else if (remains == 0){
            if (min == -1){  //min是初始值的话直接更新
                min = count.get();
            }else {
                min = Integer.min(min,count.get());
            }
        }
        count.decrementAndGet();  //自减
    }

    /**
     * ⭐贪心算法    从大往小凑   每次都选择当下的最优解  向前看  别回头
     * ⭐可能找到错误的解   但是正常的货币体系是适用的
     */
    public static int coinChange2(int[] coins, int amount){
        //首先让coins降序排列
        Arrays.sort(coins);
        int left = 0, right = coins.length - 1;
        while (left < right) {
            int temp = coins[left];
            coins[left] = coins[right];
            coins[right] = temp;
            left++;
            right--;
        }
        //贪心
        int remainder = amount;  //剩余数量
        int count = 0;
        for (int coin : coins) {
            while (remainder >= coin){  //找最大的一直减，减不动了就往外层跑
                remainder -= coin;
                count ++;
            }
            if (remainder == 0){  //等于0了退出循环  不能直接返回  因为还有别的硬币
                break;
            }
        }
        return remainder == 0 ? count : -1;
    }

    public static void main(String[] args) {
        System.out.println(coinChange2(new int[]{186,419,83,408}, 6249));  //这就是贪心处理不了的一种情况
    }
}
