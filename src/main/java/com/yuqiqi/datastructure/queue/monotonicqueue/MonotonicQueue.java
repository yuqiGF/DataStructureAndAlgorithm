package com.yuqiqi.datastructure.queue.monotonicqueue;

import java.util.LinkedList;

/**
 * 单调队列（递减）   ⭐队列中有一定的顺序
 * 先进先出  如果进的元素在中间位置的话  需要把前面的元素都删掉 再加入新的元素（原本的就没有了）
 */
public class MonotonicQueue {
    //拿原本的双端队列进行一下改造
    private LinkedList<Integer> deque = new LinkedList<Integer>();

    public Integer peek(){
        return deque.peekFirst();  //取出第一个
    }

    public void poll(){
        deque.pollFirst();  //出队第一个
    }

    //假装是一个单调减队列 ⭐就这里有点点不一样
    public void offer(Integer t){
        while (!deque.isEmpty() && t > deque.peekLast()){
            deque.pollLast();  //队尾移除
        }
        deque.offerLast(t);  //队尾添加
    }
}
