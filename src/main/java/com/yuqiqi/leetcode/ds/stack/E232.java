package com.yuqiqi.leetcode.ds.stack;

import java.util.LinkedList;

/**
 * 双栈模拟队列
 */
public class E232 {
    /**
     * 用两个栈 分别模拟队列头和队列尾   s1用来取元素 直到取空为止  s2负责加元素  s1空了的时候往s1里面倒腾
     */
    static class MyQueue {
        LinkedList<Integer> s1 = new LinkedList<>();
        LinkedList<Integer> s2 = new LinkedList<>();

        public MyQueue() {

        }

        public void push(int x) {  //往队列尾部添加  直接s2入栈
            s2.push(x);
        }

        public int pop() {  //从队列头部取出 若s1是空的 s2的元素全部出栈后入栈到s1中，然后s1出栈一个元
                            // s1不是空的的话直接出栈
            if (s1.isEmpty()) {
                while(!s2.isEmpty()){
                    s1.push(s2.pop());
                }
            }
            return s1.pop();
        }

        public int peek() {
            if (s1.isEmpty()) {
                while(!s2.isEmpty()){
                    s1.push(s2.pop());
                }
            }
            return s1.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }
}
