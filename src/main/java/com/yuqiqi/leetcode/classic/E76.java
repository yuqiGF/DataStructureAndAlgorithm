package com.yuqiqi.leetcode.classic;

import java.util.HashMap;
import java.util.Locale;

/**
 * 最小覆盖子串
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 */
public class E76 {
    /*
        思路 ⭐滑动窗口
        1、统计目标字符串中各种字符的个数，统计原始字符串中i到j范围内各个字符的个数
        2、如果不满足条件 j ++ 扩大范围 ，直到满足条件为止
        3、满足条件 i ++ 范围缩小，直到不满足条件 （现在就找到一组解了）⭐
        4、重复2和3的步骤  直到j到达字符串的末尾
     */
    public static String minWindow(String s, String t) {
        char[] target = t.toCharArray();
        char[] origin = s.toCharArray();

        //记录目标字符串中每个元素的个数
        int[] targetCount = new int[128];
        for (char c : target) {
            targetCount[c] ++;
        }

        int passTotal = 0;  //⭐⭐记录条件总数
        for (int count : targetCount) {
            if (count > 0){
                passTotal ++;
            }
        }

        int passed = 0;  //⭐已经通过的个数

        //原始字符串总各个字符出现的次数
        int[] originCount = new int[128];
        int i = 0;
        int j = 0;  //重点是找到什么时候j会暂停
        String result = "";  //结果

        while (j < origin.length){
            char right = origin[j];
            originCount[right] ++;  //计数
            //⭐判断是不是满足条件了
            if (originCount[right] == targetCount[right]){
                passed ++;  //满足条件的个数++
            }

            //⭐若已满足所有的条件 ， 缩小i的范围
            while (passed == passTotal && i <= j){
                //⭐每次一进入内层循环就表示找到解了⭐
                if (result.isEmpty() || j - i + 1 < result.length()){
                    result = s.substring(i , j + 1);
                }
                char left = origin[i];
                originCount[left] --;  //计数减少
                if (originCount[left] < targetCount[left]){  //减少到不满足条件了
                    passed --;
                }
                i ++;
            }
            j ++;
        }
        //最后返回  如果改变了  就用新的 没变就是空
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("A", "A"));
    }
}
