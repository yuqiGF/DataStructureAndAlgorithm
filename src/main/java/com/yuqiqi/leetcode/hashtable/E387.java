package com.yuqiqi.leetcode.hashtable;

import java.util.HashMap;

/**
 * 找出字符转中第一个不重复的字符   给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1
 */
public class E387 {
    //思路：哈希表   第一次遍历存入值   第二次遍历找值为1的key
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (map.containsKey(aChar)) {
                map.put(aChar, map.get(aChar) + 1);   //可以优化为是否存在？存在的话存-1   最后遍历查找第一个不为-1的值的key
            } else {
                map.put(aChar, 1);
            }
        }
        //再遍历一次
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    //优化：位图   26个字母的字符数组
    public int firstUniqChar2(String s){
        int[] key = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            key[c - 'a'] ++;  //存入位图
        }
        for (int i = 0; i < chars.length; i++) {
            if (key[chars[i] - 'a'] == 1){
                return i;  //依据原字符串在位图中找值为1的元素即可
            }
        }
        return -1;
    }
}
