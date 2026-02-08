package com.yuqiqi.leetcode.classic;

import java.util.LinkedList;

/**
 * 最长回文子串    ⭐中心开发
 */
public class E5 {
    //思路  中心开发（奇数个的时候 以一个字符为中心  偶数个的时候以俩字符为中心）
    public static String longestPalindrome(String s) {
        left = 0;  //⭐记得静态变量清零
        right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            extend(chars , i , i);  //一个字符作为中心点
            extend(chars , i , i + 1);  //两个字符作为中心点
        }
//        return s.substring(left,right);
        return new String(chars , left , right - left + 1);
    }

    public static int left = 0;  //左
    public static int right = 0;  //右
    /**
     * 中心开发
     * @param chars 原始数组
     * @param i 临时左指针
     * @param j 临时右指针
     */
    public static void extend(char[] chars , int i , int j){
        while (i >= 0 && j < chars.length && chars[i] == chars[j]){  //检测到是回文⭐
            i --;  //扩大范围
            j ++;
        }
        //到这里就出了范围 不再是回文
        i ++;
        j --;
        if (j - i > right - left){  //找到了更大的回文串
            left = i;
            right = j;
        }
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("bb"));
    }
}
