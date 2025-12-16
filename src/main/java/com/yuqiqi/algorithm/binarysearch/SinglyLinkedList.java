package com.yuqiqi.algorithm.binarysearch;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单响链表 （不带哨兵版）  ⭐本质上操作的都是节点
 */
public class SinglyLinkedList implements Iterable<Integer>{ //单向链表整体
    private Node head = null; //头指针 头节点 一开始是空的
    //value是null  next也是null

    //⭐static修饰的成员会随着类的加载而加载，而普通成员会随着对象的加载而加载  所以static里不能用非static的东西
    //能加尽量加上 提升效率
    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        //匿名内部类
        return new Iterator<Integer>() {
            Node p = head; //初始指针指向第一个节点的位置
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
        //链表为空
//        head = new Node(value,null);   //这里的head不是哨兵
        //链表非空    head指向我  我指向head原来指向的东西     ///head就是我  我指向head原本指向的东西
        head = new Node(value, head);  //可以覆盖掉链表为空的情况
    }

    /**
     * 从尾部添加  尾插
     */
    public void addLast(int value){
        //找最后一个节点
        Node last = findLast();
        //空链表的情况
        if (last == null){
            //直接新增
            addFirst(value);
            return;
        }
        //尾插  直接指向
        last.next = new Node(value,null);
    }

    /**
     * 寻找最后一个节点
     * @return
     */
    private Node findLast(){
        if (head == null){
            return null; //空链表
        }
        Node p = head;
        while (p.next != null){ //该节点的下一个节点指向的非空  就一直往后找
            p = p.next;
        }
        return p; //该节点的next是空的  所以是最后一个节点
    }

    /**
     * 链表遍历
     */
    public void loop1(Consumer<Integer> consumer){
        Node p = head; //定义指针p 初始指向第一个节点    最后一个节点指向的是null 可以用来判断是否结束
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
        for (Node p = head; p!=null ; p = p.next){
            consumer.accept(p.value);
        }
    }

    /**
     * 根据索引查找节点   因为链表本身没有索引 所以索引要自己在遍历的时候设置
     */
    private Node findNode(int index){
        int i = 0; //索引的起始位置
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
        if (node == null){
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n",index));  //参数不合法异常
        }
        return node.value;
    }

    /**
     * 往合法的索引位置加元素   找索引的上一个位置
     */
    public void insert(int index, int value){
        if (index == 0){  //直接在0的位置插入  可以 但走下面会抛异常  所以单独拿出来
            addFirst(value); //头插
            return;
        }

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
        int value;
        if (index == 0){
            return removeFirst();
        }
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
