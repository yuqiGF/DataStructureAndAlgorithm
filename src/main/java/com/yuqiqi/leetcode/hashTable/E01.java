package com.yuqiqi.leetcode.hashTable;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和   经典  给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 */
public class E01 {
    /**
     * 思路一：暴力枚举
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
    /**
     * 思路二：哈希表   循环遍历数组  以target-x作为key到哈希表中去查找   找到了返回  没找到存入哈希表 当前数字作为key  索引作为value
     */
    public int[] twoSum2(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[]{i,map.get(target - nums[i])};  //要的就是索引
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[]{};
    }
}
