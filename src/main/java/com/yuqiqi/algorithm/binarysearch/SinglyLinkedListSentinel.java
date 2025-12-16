package com.yuqiqi.algorithm.binarysearch;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单响链表 （带哨兵版）  ⭐本质上操作的都是节点
 */
public class SinglyLinkedListSentinel implements Iterable<Integer>{ //单向链表整体
    //头指针指向哨兵节点
    private Node head = new Node(666,null);  //值是什么无所谓
    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        //匿名内部类
        return new Iterator<Integer>() {
            Node p = head.next; //初始指针指向第一个节点的位置  哨兵的下一个
            @Override
            public boolean hasNext() {  //是否有下一个元素
                return p != null;
            }

            @Override
            public Integer next() { //返回当前的值 指向下一个节点
                int v = p.value;
                p = p.next;
                return v;
            }
        };
    }

    /**
     * 链表节点 内部类
     */
    private static class Node{
        int value; //值
        Node next; //下一个节点指针

        //有参构造
        public Node(int value,Node next){
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 从头插入元素 头插
     */
    public void addFirst(int value){
        insert(0,value); //直接调用向索引0位置插入
    }

    /**
     * 从尾部添加  尾插
     */
    public void addLast(int value){
        //找最后一个节点  加入哨兵后不可能返回null
        Node last = findLast();
        //尾插  直接指向
        last.next = new Node(value,null);
    }

    /**
     * 寻找最后一个节点
     * @return
     */
    private Node findLast(){
        Node p = head; //哨兵
        while (p.next != null){ //该节点的下一个节点指向的非空  就一直往后找
            p = p.next;
        }
        return p; //该节点的next是空的  所以是最后一个节点
    }

    /**
     * 链表遍历  从哨兵的下一个开始遍历
     */
    public void loop1(Consumer<Integer> consumer){
        Node p = head.next; //定义指针p 初始指向第一个节点    最后一个节点指向的是null 可以用来判断是否结束
        while(p != null){
            consumer.accept(p.value);  //函数式接口的写法 让操作在外面用lambda表达式的方式 重写accept方法自行指定
            p = p.next;  //p指向下一个节点
        }
    }

    /**
     * 遍历的for实现   和while逻辑一样
     * @param consumer 策略接口/回调接口
     */
    public void loop2(Consumer<Integer> consumer){
        for (Node p = head.next; p!=null ; p = p.next){
            consumer.accept(p.value);
        }
    }

    /**
     * 根据索引查找节点   因为链表本身没有索引 所以索引要自己在遍历的时候设置
     */
    private Node findNode(int index){
        int i = -1; //索引的起始位置  哨兵是-1  第一个位置的索引为0
        for (Node p = head; p != null ; p = p.next, i ++){
            if (i == index){  //找到了
                return p;
            }
        }
        return null;
    }

    /**
     * 暴露给外界一个方法来查找索引位置的值
     */
    public int get(int index){
        //根据索引查找节点
        Node node = findNode(index);
        if (node == null){  //索引位置不正确
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n",index));  //参数不合法异常
        }
        return node.value;
    }

    /**
     * 往合法的索引位置加元素   找索引的上一个位置
     */
    public void insert(int index, int value){
        Node former = findNode(index -1); //索引位置的上一个节点

        if (former == null){
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n",index));  //参数不合法异常
        }
        former.next = new Node(value,former.next); //上一个节点指向新节点，新节点指向下一个节点
    }

    /**
     * 删除第一个节点  直接让head指针指向第二个节点即可   java会自动把删掉的节点垃圾回收调
     */
    public int removeFirst(){
        if (head == null){
            throw new IllegalArgumentException("不合法");
        }
        int value = head.value;
        head = head.next;
        return value;
    }

    /**
     * 按照索引删除   找到索引位置的上一个节点  然后让该节点指向索引位置节点的下一个节点
     */
    public int remove(int index){
        Node former = findNode( index - 1);
        //健壮性判断
        if (former == null){
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n",index));  //参数不合法异常
        }
        Node removed = former.next;
        if (removed == null){  //被删的元素为空
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n",index));  //参数不合法异常
        }
        former.next = removed.next;
        return removed.value;
    }







}
