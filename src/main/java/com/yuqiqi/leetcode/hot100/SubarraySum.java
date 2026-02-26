package com.yuqiqi.leetcode.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数
 */
public class SubarraySum {
    public static int subarraySum(int[] nums, int k) {
        //思路 前缀和⭐   中间一长串的数据和等于   整个遍历到的位置 - 从开始到遍历起始的位置
        int res = 0;
        //map里面存前缀和
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0 , 1);  //防止第一个就是0 而且要找的就是0的情况

        //当前和
        int sum = 0;

        for (int num : nums) {
            sum += num;
            //找到了   （⭐就是一个数学的逆向思维）
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            //把这个位置的前缀和加到map中
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1}, 0));
    }
}
