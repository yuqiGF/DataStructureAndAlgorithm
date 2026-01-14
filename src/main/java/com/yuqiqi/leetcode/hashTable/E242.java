package com.yuqiqi.leetcode.hashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * 判断两个单词是否为字母异位词   字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
 */
public class E242 {
    //思路： 经典哈希表  记录值并且计数     （也可以排序后比较）
    public boolean isAnagram(String s, String t) {
        //长度不对的话直接返回
        if (s.length() != t.length()){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else {
                map.put(s.charAt(i),1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i)) && map.get(t.charAt(i)) > 0){
                map.put(t.charAt(i), map.get(t.charAt(i)) -1);
            }else {
                return false;
            }
        }
        return true;
    }

    //优化  位图  字符串转化为26个字母的数组 保存每个字母出现的次数    然后把字符串全部转化为位图 再去比较就好了
    public boolean isAnagram2(String s, String t){
        return Arrays.equals(getKey(s),getKey(t));
    }

    private int[] getKey(String s){
        int[] key = new int[26]; //26个字母的位图
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            key[c-'a'] ++;
        }
        return key;
    }

    private int[] getKey2(String s){
        int[] key = new int[26]; //26个字母的位图
        char[] chars = s.toCharArray();   //变成数组时候再去逐个转化   效率会更高  （JVM对数组的遍历有优化  局部性原理）
        for (char c : chars) {
            key[c - 'a'] ++;
        }
        return key;
    }

}
