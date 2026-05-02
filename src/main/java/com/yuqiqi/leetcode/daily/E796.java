package com.yuqiqi.leetcode.daily;

import java.util.LinkedList;
import java.util.Objects;

public class E796 {
    public static boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    public static void main(String[] args) {
        rotateString("abc" , "bca");
    }
}
