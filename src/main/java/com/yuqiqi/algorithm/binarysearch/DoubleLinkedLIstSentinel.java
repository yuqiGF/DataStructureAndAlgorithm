package com.yuqiqi.algorithm.binarysearch;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 双向链表  带哨兵 head和tail    （带哨兵的好实现一些 不用频繁的判断是否为空链表）
 * 优点：方便操作尾部
 */
public class DoubleLinkedLIstSentinel implements Iterable<Integer>{
    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next; //起始位置
            @Override
            public boolean hasNext() {  //是否有下一个元素
                return p != tail;
            }

            @Override
            public Integer next() {  //操作  返回当前的值并指向下一个元素
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     * 节点类 带上一个元素和下一个元素
     */
    static class Node{
        private Node prev; //指向上一个节点
        private int value; //值
        private Node next; //指向下一个节点

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
    //⭐哨兵
    private Node head; //头哨兵
    private Node tail; //尾哨兵

    /**
     * 构造方法
     */
    public DoubleLinkedLIstSentinel(){
        head = new Node(null,666,null);
        tail = new Node(null,888,null);
        head.next = tail;  //头指向尾
        tail.prev = head;  //尾指向头
    }

    /**
     * 根据索引位置查找节点  没有自带的索引 所以要在遍历的时候加上
     */
    private Node findNode(int index){
        int i = -1; //从头节点的索引 -1 开始
        for(Node p = head ; p != tail ; p = p.next , i ++){
           if (i == index){
               return p;
           }
        }
        return null;
    }


    public void addFirst(int value){
        insert(0,value);
    }

    public void removeFirst(){
        remove(0);
    }

    /**
     * 尾插  不用再遍历了 尾哨兵的前一个节点就是最后一个节点
     * @param value
     */
    public void addLast(int value){
        Node last = tail.prev;  //找到最后一个节点
        Node inserted = new Node(last, value, tail);
        last.next = inserted;
        tail.prev = inserted;
    }

    /**
     * 尾删 不用再遍历 性能好
     */
    public void removeLast(){
        Node removed = tail.prev; //找到最后一个节点  （尾节点的上一个节点）
        if (removed == head){
            throw new IllegalArgumentException("不能删除头哨兵节点");
        }
        Node prev = removed.prev; //到数第二个节点
        prev.next = tail;
        tail.prev = prev;
    }

    /**
     * 根据索引插入   画图更好理解   需要当前索引位置的节点和上一个位置的节点
     * @param index
     * @param value
     */
    public void insert(int index, int value){
        Node prev = findNode(index - 1);  //找当前位置的上一个节点
        if (prev == null){
            throw new IllegalArgumentException("索引越界");
        }
        Node next = prev.next;  //上一个节点的下一个节点就是要插入的元素的下一个节点
        //指定四个指针的朝向
        Node inserted = new Node(prev,value,next);
        prev.next = inserted;
        next.prev = inserted;
    }

    /**
     * 删除索引位置的元素
     * @param index
     */
    public void remove(int index){
        Node prev = findNode(index - 1); //找到索引位置的上一个元素  prev不可能是尾哨兵  因为遍历的时候遍历到尾哨兵就停了
        if (prev == null){
            throw new IllegalArgumentException("索引越界");
        }
        Node removed = prev.next; //找到被删除的元素
        if (removed == tail){
            throw new IllegalArgumentException("不能删除尾哨兵");
        }
        Node next = removed.next;  //找到当前索引位置的下一个元素

        prev.next = next;
        next.prev = prev;
    }

    /**
     * 遍历
     * @param consumer
     */
    public void loop(Consumer<Integer> consumer){
        for (Node p = head.next; p !=tail; p = p.next){
            consumer.accept(p.value);
        }
    }

}
