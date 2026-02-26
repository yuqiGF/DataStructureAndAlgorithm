package com.yuqiqi.leetcode.hot100;

import java.util.HashMap;
import java.util.HashSet;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {  //滑动窗口
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int begin = 0;
        for (int end = 0; end < chars.length; end++) {
            if (map.containsKey(chars[end])){
                begin = Math.max(begin , map.get(chars[end]) + 1);
            }
            max = Math.max(max , end - begin + 1);
            map.put(chars[end] , end);
        }
        return max;
    }
}
