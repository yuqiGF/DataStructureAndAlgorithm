package com.yuqiqi.leetcode.ds.hashtable;

import java.util.*;

/**
 * 字母异位词分组    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * ⭐用哈希表做记录  重点是排序这一步
 */
public class E49 {
    //每一组重新排序⭐后的词都是同一个⭐   然后往哈希表里面存并且比对排好序的结果就可以了
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();  //哈希表 键存排好序的字符  值存分好组的结果
//        List<List<String>> res = new ArrayList<>();  //结果集合
        for (String str : strs) {
            char[] chars = str.toCharArray();  //转化成字符数组
            Arrays.sort(chars);  //排序
            String sortStr = new String(chars);  //再转化为String
            if (map.containsKey(sortStr)){
                map.get(sortStr).add(str);  //往值集合里面加一个元素
            }else {
                map.put(sortStr,new ArrayList<>());  //没有的话直接往里面加一个
                map.get(sortStr).add(str);  //把自己放进去⭐
            }
        }
//        map.forEach((k,v) ->{  //遍历map
//            res.add(v);
//        });
//        return res;
        return new ArrayList<>(map.values());   //可以直接再构造的时候就传入元素
    }

    //法二：  只是优化一下写法   减少了map的操作次数
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();  //哈希表 键存排好序的字符  值存分好组的结果
        for (String str : strs) {
            char[] chars = str.toCharArray();  //转化成字符数组
            Arrays.sort(chars);  //排序
            String sortStr = new String(chars);  //再转化为String
            //⭐这里直接把value结果集合取出来  然后判空
            //为空的话就创建一个
            List<String> list = map.computeIfAbsent(sortStr, k -> new ArrayList<>());   //如果缺失的话则执行  lambda表达式k代表的就是新的作用域中的sortStr
            list.add(str);
        }
        return new ArrayList<>(map.values());   //可以直接再构造的时候就传入元素
    }

    //法三： ⭐位图！！  用一个代表26个字母的数组来记录单词 字母每出现一次  对应位置就加一  然后用哈希表比对每个数组是否相同
    //避免了n log n的排序
    static class ArrayKey{
        int[] key = new int[26];
        //IDEA自动生成equals和hashcode方法
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Objects.deepEquals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }
        //构造方法  将字符串转化为 位图数组
        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                char c =  str.charAt(i);
                key[c-'a']++;  //对应位置的值增加
            }
        }
    }
    public List<List<String>> groupAnagrams3(String[] strs) {
        HashMap<ArrayKey, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);
            ArrayList<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());  //缺失的话就执行lambda中的内容
            list.add(str); //把当前的这个字符串加入进去
        }
        return new ArrayList<>(map.values());
    }

}
