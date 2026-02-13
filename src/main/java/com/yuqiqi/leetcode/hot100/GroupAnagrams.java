package com.yuqiqi.leetcode.hot100;


import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        //用哈希表来存字符串结果   （⭐这几个字母排序后结果都一样）
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);  //排个序
            String sorted = new String(chars);
            if (map.containsKey(sorted)){
                map.get(sorted).add(str);
            }else {
                map.put(sorted , new ArrayList<>());
                map.get(sorted).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }
}
