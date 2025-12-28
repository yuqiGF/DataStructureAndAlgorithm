package com.yuqiqi.dataStructure.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 链表实现双端队列     双向带哨兵环形链表实现双端队列 （关键是找到正确的位置）
 */
public class LinkedListDeque<E> implements Deque<E> , Iterable<E> {
    /**
     * 节点类
     */
    static class Node<E>{
        private Node<E> prev;
        private E value;
        private Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    //初始化一些必要元素
    private final int capacity;
    private int size = 0;
    private final Node<E> sentinel = new Node<>(null , null , null); //哨兵

    //初始化
    public LinkedListDeque(int capacity){
        this.capacity = capacity;
        sentinel.next = sentinel; //尾指向头
        sentinel.prev = sentinel; //头指向尾
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()){
            return false; //满了
        }
        Node<E> a = sentinel; //前
        Node<E> b = sentinel.next; //后
        Node<E> offered = new Node<>(a , e , b);  //插入新节点
        a.next = offered;
        b.prev = offered;
        size ++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()){
            return false;
        }
        Node<E> a =  sentinel.prev; //前
        Node<E> b = sentinel; //后
        Node<E> offered = new Node<>(a , e , b);  //插入新节点
        a.next = offered;
        b.prev = offered;
        size ++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()){
            return null; //空的
        }
        Node<E> polled = sentinel.next;
        sentinel.next = polled.next;  //互删
        polled.next.prev = sentinel;
        size --;
        return polled.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()){
            return null; //空的
        }
        Node<E> polled = sentinel.prev;
        sentinel.prev = polled.prev; //互删
        polled.prev.next = sentinel;
        size --;
        return polled.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()){
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()){
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next; //遍历指针
            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
