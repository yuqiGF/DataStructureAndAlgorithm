package com.yuqiqi.leetcode;

/**
 * 字符串匹配
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class E28 {
    //暴力循环
    public static int strStr(String haystack, String needle) {
        char[] origin = haystack.toCharArray();  //原始字符串转化为数组
        char[] pattern = needle.toCharArray();
        for (int i = 0; i < origin.length - pattern.length + 1; i++) {
            int temp = i;
            boolean flag = true;  //没找到
            for (char c : pattern) {
                if (origin[i] != c) {
                    flag = false;
                    break;
                }
                i ++;
            }
            i = temp;  //⭐ i 回溯
            if (flag){
                return i;
            }
        }
        return -1;
    }

    //KMP算法
    public static int kmp(String haystack , String needle){


        return -1;
    }



    public static void main(String[] args) {
        System.out.println(strStr("a", "a"));
    }
}
