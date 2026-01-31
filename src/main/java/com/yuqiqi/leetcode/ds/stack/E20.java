package com.yuqiqi.leetcode.ds.stack;

import com.yuqiqi.datastructure.stack.LinkedListStack;

/**
 * ⭐有效的括号   给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class E20 {
    /**
     * 遍历
     * ⭐如果遇到左括号，那么就需要把对应的右括号放入栈内
     * ⭐如果遇到右括号，就和栈顶对比，一样就出栈，不一样就接着入栈
     * ⭐ java中linkedList也可以当作栈来使用 当然也有现成的Stack用
     */

    public boolean isValid(String s) {
        //定义栈
        LinkedListStack<Character> stack = new LinkedListStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                stack.push(')'); //放入相反的括号
            }
            else if (c == '['){
                stack.push(']');
            }
            else if (c == '{'){
                stack.push('}');
            }
            else {
                if (!stack.isEmpty() && c == stack.peek()){  //栈非空时 取出
                    stack.pop(); //出栈
                }else {
                    return false;  //不等的话直接return false  是个无效括号
                }
            }
        }
        //遍历结束，如果栈是空的  那么就是有效括号
        return stack.isEmpty();
    }
}
