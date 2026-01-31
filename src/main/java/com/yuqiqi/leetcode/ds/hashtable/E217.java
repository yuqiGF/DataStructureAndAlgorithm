package com.yuqiqi.leetcode.ds.hashtable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 存在重复元素  给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 */
public class E217 {
    //思路  hash表   秒杀！！
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)){
                return true;
            }else {
                map.put(num,1);   //这里调用了两次map的方法  会比较慢  改为hashSet反而会效果更好  因为它底层是基于hashMap实现 但是只调用了一次
            }
        }
        return false;
    }
    public boolean containsDuplicate2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)){   //set底层调用map的put方法  map的put方法如果遇到已存在的情况，默认返回map中已有的上一个值，并替换为新值
                return true;
            }
        }
        return false;
    }

}
