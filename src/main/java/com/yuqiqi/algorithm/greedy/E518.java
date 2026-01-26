package com.yuqiqi.algorithm.greedy;

import java.util.Arrays;
import java.util.Collections;

/**
 * 零钱兑换Ⅱ
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数
 */
public class E518 {
    public int change(int amount, int[] coins) {
        //反转
        int left = 0, right = coins.length - 1;
        while (left < right) {
            int temp = coins[left];
            coins[left] = coins[right];
            coins[right] = temp;
            left++;
            right--;
        }
        return rec(0,coins,amount);
    }

    /**
     * ⭐方法一：暴力递归
     * @param index 当前处理的硬币的序号
     * @param coins 硬币数组
     * @param remainder 剩余金额
     * @return 情况数
     */
    private int rec(int index, int[] coins, int remainder) {
        //情况1 剩余金额 < 0 无解
        if (remainder < 0){
            return 0;
        }
        //情况2 剩余金额 > 0 继续递归
        else if (remainder > 0){
            int count = 0;
            for (int i = index; i < coins.length; i++) {  //循环里面递归 多路递归   1开始的递归一大堆 2开始的又递归一大堆………………
                count += rec(i,coins,remainder - coins[i]);
            }
            return count;
        }
        //情况3 剩余金额 = 0 找到了
        else {
            return 1;
        }
    }
}
