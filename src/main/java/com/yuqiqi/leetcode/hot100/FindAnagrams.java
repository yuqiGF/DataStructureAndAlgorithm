package com.yuqiqi.leetcode.hot100;

import java.util.*;

//字母异位词
public class FindAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        //⭐⭐关键是两个判断条件  map元素个数  和 count匹配数量
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> match = new HashMap<>();
        for (char c : pc) {
            map.merge(c , 1 , Integer::sum);
        }
        int count = 0;  //满足数
        int left = 0;
        for (int right = 0; right < sc.length; right++) {

            match.merge(sc[right] , 1 , Integer::sum);

            if (map.containsKey(sc[right])){
                if (Objects.equals(map.get(sc[right]), match.get(sc[right]))){
                    count ++;
                }
            }
            //用窗口长度来判断
            if (p.length() == right - left + 1){
                //找到了
                if (map.size() == count){
                    result.add(left);
                }
                //更新
                if (Objects.equals(match.get(sc[left]), map.get(sc[left]))){
                    count --;
                }
                match.merge(sc[left] , -1 , Integer::sum);
                left ++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("baa", "aa"));
    }
}
