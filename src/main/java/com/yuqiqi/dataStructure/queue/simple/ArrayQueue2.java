package com.yuqiqi.dataStructure.queue.simple;

import java.util.Iterator;

/**
 * 唤醒数组实现队列   判空方法：单独维护一个size变量 来存储当前队列中的元素数量
 */
public class ArrayQueue2<E> implements Queue<E>,Iterable<E> {
    //初始化
    private E[] array;
    private int head;
    private int tail;
    private int size;  //队列中的元素数量

    //构造器 初始化初始变量
    @SuppressWarnings("all")
    public ArrayQueue2(int capacity){
        array = (E[]) new Object[capacity]; //不用单独空出来一个了
    }

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false; //满了
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        E value = array[head];
        array[head] = null;
        head = (head + 1) % array.length;
        size --;
        return value;
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
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0; //遍历的时候记一下数
            @Override
            public boolean hasNext() {
                return count < size; //计数没达到已有元素数量
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                count ++;
                return value;
            }
        };
    }
}
