package com.yuqiqi.leetcode;


/**
 * 最长公共前缀
 */
public class E14 {
    public String longestCommonPrefix(String[] strs) {
        //思路 拿出第一个的每个字符来和后面的比较   先左右比 然后上下比⭐
        String start = strs[0];  //取第一个
        char[] s = start.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {  //开始和第一个字符串挨个比较   左右比⭐
            for (int j = 1; j < strs.length; j++) {  //剩下的每个字符串的第i个字符  上下比⭐
                if (strs[j].length() == i || strs[j].charAt(i) != s[i]) {  //遇到了不一样的
                    return sb.toString();
                }
            }
            sb.append(s[i]);  //全部匹配  拼串
        }
        return sb.toString();
    }
}
