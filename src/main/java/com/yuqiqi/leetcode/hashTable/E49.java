package com.yuqiqi.leetcode.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * ⭐用哈希表做记录  重点是排序这一步
 */
public class E49 {
    //每一组重新排序⭐后的词都是同一个⭐   然后往哈希表里面存并且比对排好序的结果就可以了
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();  //哈希表 键存排好序的字符  值存分好组的结果
        List<List<String>> res = new ArrayList<>();  //结果集合
        for (String str : strs) {
            char[] chars = str.toCharArray();  //转化成字符数组
            Arrays.sort(chars);  //排序
            String sortStr = Arrays.toString(chars);  //再转化为String
            if (map.containsKey(sortStr)){
                map.get(sortStr).add(str);  //往值集合里面加一个元素
            }else {
                map.put(sortStr,new ArrayList<>());  //没有的话直接往里面加一个
                map.get(sortStr).add(str);  //把自己放进去⭐
            }
        }
        map.forEach((k,v) ->{  //遍历map
            res.add(v);
        });
        return res;
    }
}
