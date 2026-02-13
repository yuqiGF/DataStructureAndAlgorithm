package com.yuqiqi.leetcode.hot100;

import java.util.HashSet;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        //哈希分组
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0; //记录结果
        for (Integer i : set) {  //挨个遍历
            int temp = 1;
            if(!set.contains(i - 1)){  //不存在前驱时才一直往后遍历  剪枝？\
                while (set.contains(i + 1)){
                    i = i + 1;
                    temp ++;
                }
            }
            max = Math.max(max , temp);
        }
        return max;
    }
}
