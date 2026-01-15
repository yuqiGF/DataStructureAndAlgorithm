package com.yuqiqi.leetcode.hashTable;

import java.util.*;

/**
 * 最常见的单词   给你一个字符串 paragraph 和一个表示禁用词的字符串数组 banned ，返回出现频率最高的非禁用词。题目数据 保证 至少存在一个非禁用词，且答案 唯一 。
 * paragraph 中的单词 不区分大小写 ，答案应以 小写 形式返回
 */
public class E819 {
    //思路  按照空格分割  然后逐个存入哈希表计数   爬虫思想  两次遍历哈希表
    public static String mostCommonWord(String paragraph, String[] banned) {
        String s0 = paragraph.toLowerCase();  //变小写
        String s1 = s0.replace(",", " ").trim();
        String s2 = s1.replace(".", " ").trim();  //去符号
        String s3 = s2.replace("!", " ").trim();  //去符号
        String s4 = s3.replace("?", " ").trim();  //去符号
        String s5 = s4.replace(";", " ").trim();  //去符号
        String s6 = s5.replace("'", " ").trim();  //去符号

        String[] strings = s6.split(" "); //分词
        HashMap<String, Integer> map = new HashMap<>();
        for (String string : strings) {
            boolean flag = false;  //是否是违禁词
            //违禁词压根不忘里放
            for (String s : banned) {
                if (s.equals(string)) {
                    flag = true;
                    break;
                }
            }
            if (!flag && map.containsKey(string)) {
                map.put(string,map.get(string) + 1);
            }else if (!flag  && string != null && string.isEmpty()){  //这里记得不能把空字符串放进来
                map.put(string,1);
            }
        }
        //遍历map取值
        String result = null;
        int count = 0;
        Object[] keys = map.keySet().toArray();   //可以用entrySet代替
        System.out.println(Arrays.toString(keys));
        for (int i = 0; i < map.size(); i++) {
            if (map.get(keys[i].toString()) > count){
                count = map.get(keys[i].toString());
                result = keys[i].toString();
            }
        }
        return result;
    }

    //优化
    public static String mostCommonWord2(String paragraph, String[] banned){
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");  //⭐用正则表达式来分割
        Set<String> set = Set.of(banned); //⭐将违禁词转化为set集合
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : split) {
//            Integer value = map.get(s);  //直接取值 没有的话往里加  减少两次map操作
//            if (value == null){
//                value = 0;
//            }
//            map.put(s,value +1);
            if (!set.contains(s)){  //⭐ 不包含违禁词的话  才会放入
                map.compute(s, (k,v)-> v == null ? 0 : v + 1);  //compute api⭐和上面四行完全等价
            }
        }
        Optional<Map.Entry<String, Integer>> optional = map.entrySet().stream().max(Map.Entry.comparingByValue());
        return optional.map(Map.Entry::getKey).orElse(null);
    }

    //再优化
    public static String mostCommonWord3(String paragraph, String[] banned){
        Set<String> set = Set.of(banned); //⭐将违禁词转化为set集合
        HashMap<String, Integer> map = new HashMap<>();

        char[] chars = paragraph.toLowerCase().trim().toCharArray();  //转化为字符数组
        StringBuilder sb = new StringBuilder(); //⭐用stringBuilder拼接 效率更高
        for (char ch : chars) {
            if (ch >= 'a' && ch <= 'z'){
                sb.append(ch);
            }else {  //遇到不是a到z的时候就整合成字符串 放入map集合
                String key = sb.toString();
                if (!key.isEmpty() && !set.contains(key)){
                    map.compute(key,(k,v)-> v == null ? 1 : v + 1);
                }
//                sb = new StringBuilder();  //更新sb
                sb.setLength(0);  //清空
            }
        }
        //加一步避免单个单词无法分割的情况
        if (sb.length() > 0){
            String key = sb.toString();
            if (!set.contains(key)){
                map.compute(key,(k,v)-> v == null ? 1 : v + 1);
            }
        }

        //用EntrySet遍历  比lambda快
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String,Integer> e: map.entrySet()){
            Integer value = e.getValue();  //从节点类中获取到value
            if (value > max){
                max = value;
                maxKey = e.getKey();
            }
        }
        return maxKey;
    }

    public static void main(String[] args) {
        System.out.println(mostCommonWord3("Bob. hIt, baLl", new String[]{"bob", "hit"}));
    }
}
