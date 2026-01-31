package com.yuqiqi.leetcode.daily;

/**
 * 寻找比给定字符大的最小字符
 * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
 * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
 */
public class E744 {
    public char nextGreatestLetter(char[] letters, char target) {
        //既然已经拍好了  遍历即可
        for (char letter : letters) {
            if (letter > target){
                return letter;
            }
        }
        return letters[0];
    }
}
