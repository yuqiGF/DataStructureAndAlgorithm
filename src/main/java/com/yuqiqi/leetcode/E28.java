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

    //KMP算法   ⭐省去重复的对比  让外层原始字符串再匹配的时候少回退几个
    //⭐匹配最长前后缀（不包含全部）  匹配到了的话就直接跳转到对应的位置  为空的话直接跳过 从头再开始
    /*
        ⭐最长前后缀数组⭐ 只和模式字符串相关   索引指的是使用了模式的前j个字符串 - 1   值指的是最长前后缀的长度（恰好是不匹配时j要跳转到的索引位置）
        匹配成功：i ++  j ++ ,直到j==模式字符串的长度
        匹配失败：j!=0时 跳过最长前后缀字符    j==0时 i++ 直接下一个匹配
        想象：模式字符串在原字符串上面跳动
     */

    public static int kmp(String haystack, String needle){
        char[] origin = haystack.toCharArray();
        char[] pattern = needle.toCharArray();

        int[] lps = lps(pattern);   //最长前后缀生成⭐
        int i = 0;
        int j = 0;
        while (pattern.length - j <= origin.length - i){  //匹配字符串未超出原字符串
            if (origin[i] == pattern[j]){  //找到相同的字符
                i ++;
                j ++;
            }else if (j == 0){  //没找到1： 从头匹配
                i ++;  //i移动 模式串还是索引0位置来和它匹配
            }else {  //没找到2： 从lsp中选
                j = lps[j - 1];  //使用了前j个字符串，跳到数组中对应j-1值的位置
            }
            if (j == pattern.length){
                return i - j;  //⭐匹配成功
            }
        }
        return -1;
    }

    /**
     * 生成最长前后缀数组    只要模式字符串就够了   两个这样的字符串进行一个匹配
     * ⭐⭐最长前后缀数组⭐ 只和模式字符串相关   索引指的是使用了模式的前j个字符串 - 1
     * @param pattern 模式字符串
     * @return 最长前后缀数组
     */
    private static int[] lps(char[] pattern) {
        int[] lps = new int[pattern.length];
        int i = 1;  //⭐⭐重点   错位匹配
        int j = 0;
        while (i < pattern.length){
            //遇到相同字符  记录j+1为共同前后缀长度  存到lps的i索引处  然后i++ j++
            if (pattern[i] == pattern[j]) {
                lps[i] = j + 1;
                i ++;
                j ++;
            }
            //遇到不同字符
            else if (j == 0){  //没有共同部分
                i ++;
            }else {  //有共同部分
                j = lps[j - 1];  //（j - 1）就表示前面用了几个字符  去数组里找对应的长度即可
            }
        }
        return lps;
    }


    public static void main(String[] args) {
        System.out.println(kmp("a", "a"));
    }
}
