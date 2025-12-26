package com.yuqiqi.dataStructure.queue;

import java.util.Iterator;

/**
 * ⭐环形数组实现队列    通过模除计算索引来实现成环操作  ⭐可以理解为本身就是一个每个索引固定好的圈圈  ⭐索引位置计算：（当前索引+要移动的索引）%数组容量
 * 判空方法：当头指针和尾指针指向同一个位置的时候为空
 * 判满方法：当尾指针的位置的下一个位置模除数组容量等于头指针时队列满 （专门空出来一个位置来判断是否为满的）
 */
public class ArrayQueue1<E> implements Queue<E>,Iterable<E> {

    //初始化
    private final E[] array;
    private int head = 0; //头指针
    private int tail = 0; //尾指针

    //构造器  容量在构造的时候指定
    @SuppressWarnings("all") //⭐抑制警告产生的注解
    public ArrayQueue1(int capacity) {  //传入期望的容量
        array = (E[]) new Object[capacity + 1]; //因为需要空出一个地方来判断是否为满，所以要创建期望容量+1的数组
    }

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false; //新增失败
        }
        //尾部插入元素
        array[tail] = value;
        tail = (tail + 1) % array.length; //tail后移一位
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        //从头部移除，移除后头指针后移即可
        E polled = array[head];
        array[head] = null; //⭐删除掉原本的元素 避免内存泄漏
        head = (head + 1) % array.length; //head后移   不移动tail的原因是避免移动元素导致的复杂度
        return polled;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail; //头指针和尾指针位置一样就是空的
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;  //尾指针索引位置的下一个位置是头指针
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
                E element = array[p];
                p = (p + 1) % array.length;
                return element;
            }
        };
    }
}
