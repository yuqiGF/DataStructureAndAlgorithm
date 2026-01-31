package com.yuqiqi.leetcode.ds.stack;

import java.util.LinkedList;

/**
 * 用队列模拟栈    每次入队的时候都把其他元素出队一下，然后重新入队，这样就是栈的顺序了
 */
public class E225 {
    class MyStack {
        //初始队列
        LinkedList<Integer> queue;
        //记录size
        private int size = 0;
        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            for (int i = 0 ; i < size ; i ++) {
                queue.offer(queue.poll());
            }
            size ++;
        }

        public int pop() {
            size --;
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
