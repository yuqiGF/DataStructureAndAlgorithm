package com.yuqiqi.leetcode.ds.stack;

import java.util.Stack;

/**
 * 逆波兰表达式  给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * 好处 不用考虑运算的优先级  对计算机很友好
 */
public class E150 {
    /**
     * ⭐解法  从左到右依次进栈  遇到运符了出栈三个元素  再将结果入栈  直到栈中只有一个元素
     * 注意操作数的顺序   （无需考虑表达式的正确性）
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    Integer b = Integer.valueOf(stack.pop()); //取出第一个数
                    Integer a = Integer.valueOf(stack.pop()); //取出第一个数
                    stack.push(String.valueOf(a + b)); //结果入栈
                }
                case "-" -> {
                    Integer b = Integer.valueOf(stack.pop()); //取出第一个数
                    Integer a = Integer.valueOf(stack.pop()); //取出第一个数
                    stack.push(String.valueOf(a - b)); //结果入栈
                }
                case "*" -> {
                    Integer b = Integer.valueOf(stack.pop()); //取出第一个数
                    Integer a = Integer.valueOf(stack.pop()); //取出第一个数
                    stack.push(String.valueOf(a * b)); //结果入栈
                }
                case "/" -> {
                    Integer b = Integer.valueOf(stack.pop()); //取出第一个数
                    Integer a = Integer.valueOf(stack.pop()); //取出第一个数
                    stack.push(String.valueOf(a / b)); //结果入栈
                }
                default -> stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
