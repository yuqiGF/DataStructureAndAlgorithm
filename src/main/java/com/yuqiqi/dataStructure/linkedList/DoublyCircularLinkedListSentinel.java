package com.yuqiqi.dataStructure.linkedList;

import java.util.Iterator;

/**
 * 双向环形链表  哨兵即作为头 又作为尾   可以用作轮询调度和环形缓冲区等
 */
public class DoublyCircularLinkedListSentinel implements Iterable<Integer>{

    /**
     * 迭代器
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next; //从哨兵的下一个节点开始遍历

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     * 节点类
     */
    private static class Node{
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 哨兵 头尾都指向自己
     */
    private final Node sentinel = new Node(null , -1,null);

    /**
     * 无参构造
     */
    public DoublyCircularLinkedListSentinel() {
        sentinel.prev = sentinel; //头尾都指向自己
        sentinel.next = sentinel;
    }

    /**
     * 头插  哨兵节点后面插  （画图好理解  循环的一个圈圈 ）找被插入的元素的前后两个节点 即找哨兵和哨兵后的一个节点
     */
    public void addFirst(int value){
        //情景： sentinel <-> node1 <-> node2 <-> sentinel
        //          a    <->  b    <-> node2 <-> sentinel
        //          a    <-> added <->   b   <-> node2    <-> sentinel
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a,value,b);
        a.next = added;
        b.prev = added;
    }

    /**
     * 尾插  往哨兵前面插入   找被插入元素的前后两个节点
     */
    public void addLast(int value){
        Node a = sentinel.prev;  //原本的最后一个节点
        Node b = sentinel;
        Node added = new Node(a,value,b);
        a.next = added;
        b.prev = added;
    }

    /**
     * 头删  删掉第一个  即sentinel的后一个  （为了好理解 也是找它的上一个和下一个节点）
     */
    public void removeFirst(){
        Node removed = sentinel.next;  //被删的
        //⭐哨兵节点不能被删掉  无害 但是浪费资源
        if (removed == sentinel){
            throw new IllegalArgumentException("不能删除哨兵节点");
        }
        Node a = sentinel; //被删的上一个
        Node b = removed.next;  //被删的下一个
        a.next = b;
        b.prev = a;
    }

    /**
     * 尾删
     */
    public void removeLast(){
        Node removed = sentinel.prev; //被删的
        //⭐哨兵节点不能被删掉
        if (removed == sentinel){
            throw new IllegalArgumentException("不能删除哨兵节点");
        }
        Node a = removed.prev; //被删掉上一个
        Node b = sentinel; //被删的下一个
        a.next = b;
        b.prev = a;
    }

    /**
     * ⭐根据值删除   其实就是遍历 找到后删除
     */
    public void removeByValue(int value){
        Node toRemove = findByValue(value);
        if (toRemove == null){
            throw new IllegalArgumentException("链表中无当前值");
        }
        Node a = toRemove.prev;
        Node b = toRemove.next;
        a.next = b;
        b.prev = a;
    }

    /**
     * 根据值找节点
     */
    private Node findByValue(int value){
        Node p = sentinel.next;
        while(p != sentinel){
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
}
