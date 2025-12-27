package com.yuqiqi.leetcode.stack;

import java.util.LinkedList;

/**
 * ⭐中缀表达式转后缀表达式
 */
public class InfixToSuffix {
    /**
     * 算法文字描述：
     * 1、遍历字符串  遇到非运算符，直接拼新串
     * 2、遇到运算符  比较栈顶运算符的优先级  如果栈顶的优先级大  则栈中优先级>=该运算符的所有运算符出栈拼串  如果自己的优先级比栈顶的小，则入栈
     * 3、遍历完成后栈中的运算符依次出栈
     * 注：括号不作为运算符（优先级为默认的-1），而是作为边界条件 遇到左括号 直接入栈，再遇到右括号的时候弹出栈内的所有运算符，直到与之匹配的括号出栈
     */


    /**
     * 指定运算符的优先级
     * @return 优先级
     */
    static int priority(char c){
        return switch (c){
            case '*','/' -> 2;
            case '+','-' -> 1;
            default -> -1;
        };
    }

    /**
     * 中缀表达式转后缀
     * @return 转换结果
     */
    static String infixToSuffix(String exp){
        LinkedList<Character> stack = new LinkedList<>(); //创建栈
        StringBuilder sb = new StringBuilder(exp.length()); //拼接结果

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i); //获取当前字符
            switch (c){
                case '+','-','*','/' ->{  //是运算符 判断进出栈
                    if (stack.isEmpty()){
                        stack.push(c); //栈是空的 直接入栈
                    }else {  //栈非空 判断优先级
                        if (priority(c) > priority(stack.peek())){
                            stack.push(c); //入栈
                        }else {
                            while(!stack.isEmpty() && priority(stack.peek()) >= priority(c) && stack.peek() != '('){ //⭐处理连续括号的情况
                                sb.append(stack.pop()); //出栈所有比当前运算符大的符号 并拼接
                            }
                            stack.push(c); //记得入栈自己（循环外）
                        }
                    }
                }
                case '(' -> stack.push(c);  //左括号 直接入栈
                case ')' -> {
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop()); //出栈
                    }
                    stack.pop(); //出栈正括号
                }

                default -> sb.append(c);  //不是运算符直接拼接
            }
        }
        //循环结束 整个栈内的运算符出栈
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
