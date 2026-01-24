package com.yuqiqi.datastructure.stack;

import java.util.Iterator;

/**
 * ⭐栈的数组实现    把右边作为顶部  左边作为底部  初始栈顶指针在0索引的位置，每新增一个元素栈顶指针向右移动一位，直到填满数组
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> , Iterable<E> {

    private E[] array; //初始数组
    private int top = 0; //栈顶指针  指的是下一个要插入的位置

    //初始化数组
    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if (isFull()){
            return false; //满了
        }
        array[top] = value;
        top ++; //栈顶指针后移
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null; //空的
        }
        E popped = array[top - 1];  //取出栈顶元素
        top --;
        return popped;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = 0;  //从底往顶遍历
            @Override
            public boolean hasNext() {
                return p < top;  //遍历到栈顶了
            }

            @Override
            public E next() {
                E value = array[p];
                p ++;
                return value;
            }
        };
    }
}
