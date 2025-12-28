package com.yuqiqi.dataStructure.queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 链表实现队列  单向环形带哨兵的链表
 */
public class LinkedListQueue<E> implements Queue<E> , Iterable<E> {
    /**
     * 节点类
     */
    private static class Node<E> {
        E value;
        Node<E> next;

        //构造器
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    //⭐头指针一直指向哨兵   尾指针初始在头节点位置，后续动态移动
    private Node<E> head = new Node<>(null, null);  //new一个哨兵节点出来
    private Node<E> tail = head;
    private int size; //当前节点数
    private int capacity = Integer.MAX_VALUE; //⭐容量  默认是最大整型

    {
        tail.next = head;  //⭐初始化代码块，优先执行  多个构造方法的时候把其中公用的东西提取出来初始化 提高效率
    }

    //有参构造器指定容量
    public LinkedListQueue(int capacity){
        this.capacity = capacity;
    }

    //无参构造器 让尾指向头（哨兵） 实现循环
    public LinkedListQueue(){
    }

    @Override
    public boolean offer(E value) {
        //入队的时候判断是否已满
        if (isFull()){
            return false;  //队列已满 插入失败
        }
        Node<E> added = new Node<>(value,head); //新节点指向头（哨兵）
        tail.next = added; //尾节点后面插入新节点
        tail = added; //尾节点更新为新增的节点
        size ++; //节点数增加
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null; //判空
        }
        Node<E> first = head.next; //获取到被移除的节点
        head.next = first.next; //删除逻辑
        //⭐特殊情况处理  只有一个节点
        if (first == tail){ //删除的刚好是队尾节点时
            tail = head;  //尾指针指回哨兵  代表队列为空
        }
        size --; //节点数减少
        return first.value;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null; //队列为空
        }
        return head.next.value;  //取值
    }

    @Override
    public boolean isEmpty() {
        return head == tail; //⭐头就是尾了就是空的   只有一个哨兵
    }

    @Override
    public boolean isFull() {
        return size == capacity; //节点数是否达到容量
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next; //哨兵节点的下一个
            @Override
            public boolean hasNext() { //是否有下一个元素
                return p != head; //指回头哨兵的时候结束
            }

            @Override
            public E next() {  //对下一个元素的操作 每次返回这个值
                E value = p.value; //取值
                p = p.next; //指针后移
                return value;
            }
        };
    }
}
