package com.yuqiqi.algorithm.divideandconquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 最长子字符串   力扣395题   ⭐分治思想
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 如果不存在这样的子字符串，则返回 0。
 * 整体思路：找出现次数少于k的 拆字串  然后在字串里面递归着继续拆 ， 直到长度不够 或者没有进行“拆”这一操作（记录此时的字符串长度）
 */
public class LongestSubstring {
    public int max = 0;  //⭐定义属性值 记录最大长度
    public int longestSubstring(String s, int k) {
        if (s.length() < k){
            return 0;  //⭐结束条件  长度足够小了  落选
        }

        //计数 （也可以用字符数组）
        HashMap<Character, Integer> map = new HashMap<>();   //因为只有英文字母  所以可以直接用数组
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == null){
                map.put(s.charAt(i) , 1);
            }else {
                map.put(s.charAt(i) , map.get(s.charAt(i)) + 1);
            }
        }

        //替换
        boolean flag = false;  //记录是否执行了替换
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < k){
                s = s.replace(entry.getKey(), ' ');
                flag = true;
            }
        }
        if (!flag){
            return s.length();  //没有比给定长度小的  就直接返回长度
        }

        //切分 递归
        String[] split = s.split(" ");
        for (String s1 : split) {
            int count = longestSubstring(s1 , k);
            if (count > max){
                max = count;
            }
        }
        return max;
    }
}
