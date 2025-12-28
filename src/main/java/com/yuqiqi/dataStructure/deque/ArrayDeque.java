package com.yuqiqi.dataStructure.deque;

import javax.swing.plaf.basic.BasicArrowButton;
import java.util.Iterator;

/**
 * 循环数组实现双端队列    有头尾指针    通过索引计算来实现循环
 * ⭐插入：尾插- 加入元素  然后tail后移  头插- head前移  然后加入元素
 * ⭐删除：头删- 删除元素 然后head后移   尾删- tail前移 然后删除元素
 * ⭐判空：head == tail   判满：head-tail恰好等于数组大小-1   空出来一个位置用来判断    或者也可以单独维护一个size来计数
 */
public class ArrayDeque<E> implements Deque<E> , Iterable<E> {


    /**
     * 指针右移后确定索引位置
     * @param index 当前索引位置
     * @param length 数组长度
     * @return 新的索引位置
     */
    public int inc(int index , int length){
        if (index + 1>= length){
            return 0;  //去到开头
        }
        return index + 1;
    }

    /**
     * 指针左移后确定索引位置
     * @param index 当前索引位置
     * @param length 数组长度
     * @return 新的索引位置
     */
    public int dec(int index , int length){
        if (index - 1 < 0){
            return length - 1; //去到末尾
        }
        return index - 1;
    }

    //初始化数组
    E[] array;
    int head;  //头
    int tail;  //尾

    @SuppressWarnings("all")
    public ArrayDeque(int capacity) {
        array = (E[]) new Object[capacity + 1]; //要创建一个容量加1的数组 空出来的一个位置用来确定是否已满
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()){
            return false; //满了
        }
        head = dec(head, array.length);  //头指针向左（循环）
        array[head] = e;  //插入
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()){
            return false; //满了
        }
        array[tail] = e; //插入
        tail = inc(tail , array.length); //尾指针右移
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()){
            return null; //空的
        }
        E e = array[head];
        array[head] = null;  //先删除  内存释放
        head = inc(head, array.length); //头右移
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()){
            return null; //空的
        }
        tail = dec(tail , array.length); //尾左移
        E e = array[tail];
        array[tail] = null;  //再删除  内存释放  帮助垃圾回收
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()){
            return null; //空的
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()){
            return null; //空的
        }
        return array[dec(tail , array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;  //头尾重合  空的
    }

    @Override
    public boolean isFull() {  //尾加一等于头的时候就满了
        return inc(tail , array.length) == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head; //遍历指针
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E e = array[p];
                p = inc(p , array.length);
                return e;
            }
        };
    }
}
