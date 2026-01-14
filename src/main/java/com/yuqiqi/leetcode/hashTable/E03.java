package com.yuqiqi.leetcode.hashTable;

import java.util.HashMap;
import java.util.Objects;

/**
 * 无重复字符的最长字串   给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度
 */
public class E03 {
    //思路：滑动窗口⭐
    // 用begin和end记录开始位置和结束位置
    // 用哈希表检查重复字符    哈希表里面存该值和其索引
    // 从左向右查看每个字符  如果没遇到重复的 就调整end  如果遇到了重复的 就调整begin到最大的重复元素的下一个位置 然后将当前的字符放入哈希表 同时调整end   end持续移动
    // 长度 = end - begin + 1
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int res = 0;
        for (int end = 0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))){
                begin = Math.max(begin,map.get(s.charAt(end)) + 1);  //更新begin为map中重复索引位置的后一个   //防回退机制⭐
            }
            map.put(s.charAt(end),end);
            res = Math.max(res,end-begin+1);  //更新res
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abbaaac"));
    }
}
