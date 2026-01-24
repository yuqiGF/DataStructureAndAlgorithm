package com.yuqiqi.datastructure.stack;

import java.util.Iterator;

/**
 * ⭐链表实现栈  单向链表配哨兵
 * @param <E> 栈里数据的类型
 */
public class LinkedListStack<E> implements Stack<E>,Iterable<E>{
    //节点类
    static class Node<E>{
        E value;
        Node<E> next;
        public Node(E value , Node<E> next){
            this.value = value;
            this.next = next;
        }
    }

    //初始化属性
    private final int capacity;  //栈的容量
    private int size; //栈中元素的个数
    private final Node<E> head = new Node<>(null,null); //头指针  哨兵

    //构造方法初始化
    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean push(E value) {
        if (isFull()){
            return false; //满了
        }
        head.next = new Node<>(value,head.next);  //头插
        size ++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null; //空的
        }
        Node<E> popped = head.next; //取出被删的元素
        head.next = popped.next;  //头删  哨兵不懂
        size --;
        return popped.value;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;  //大小为0 就是空的
    }

    @Override
    public boolean isFull() {
        return size == capacity;  //大小达到容量上限
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head;
            @Override
            public boolean hasNext() {
                return p.next != null;
            }

            @Override
            public E next() {
                Node<E> node = p.next;
                p = p.next;
                return node.value;
            }
        };
    }
}
