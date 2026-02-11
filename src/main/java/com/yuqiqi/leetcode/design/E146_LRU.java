package com.yuqiqi.leetcode.design;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 设计LRU缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，⭐则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class E146_LRU {

    //思路：双向链表+哈希  ⭐也可以直接用LinkedHashMap
    class LRUCache {
        //节点类
        static class Node{
            Node prev;
            Node next;
            int key;
            int value;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        //⭐带哨兵的双向链表 （手写一个）
        static class DoublyLinkedList{
            Node head;  //头哨兵
            Node tail;  //尾哨兵

            public DoublyLinkedList() {
                head = tail = new Node();  //哨兵
                head.next = tail;
                tail.prev = head;
            }

            //头部添加  O（1）
            public void addFirst(Node newFirst){
                Node oldFirst = head.next;
                head.next = newFirst;
                oldFirst.prev = newFirst;
                newFirst.next = oldFirst;
                newFirst.prev = head;
            }

            //尾部删除
            public Node removeLast(){
                Node last = tail.prev;
                remove(last);
                return last;
            }

            //已知节点的删除（用于更新）  ⭐传入map中传来的node 不用全表扫描了⭐
            public void remove(Node node){
                Node prev = node.prev;
                Node next = node.next;
                prev.next = next;
                next.prev = prev;
                node.next = null;  //辅助GC
            }
        }

        //⭐哈希表 + 双向链表
        private final HashMap<Integer , Node> map = new HashMap<>();
        private final DoublyLinkedList list = new DoublyLinkedList();
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)){
                return -1;
            }
            Node node = map.get(key);
            //更新链表中的元素
            list.remove(node);
            list.addFirst(node);  //删掉再加到头部
            return node.value;
        }

        public void put(int key, int value) {    //⭐不一定总是加的 只在新增时删掉最开始的元素
            if (map.containsKey(key)){
                //更新
                Node node = map.get(key); //获取到那个node
                node.value = value;
                list.remove(node);
                list.addFirst(node);
            }else {
                //新增
                Node node = new Node(key, value); //待加入的node
                map.put(key,node);
                list.addFirst(node);
                if (map.size() > capacity){  //满了
                    map.remove(list.removeLast().key);  //链表和哈希表都删
                }
            }
        }
    }

    class test {   //linkedList的remove方法有坑⭐
        private HashMap<Integer , Integer> map = new HashMap<>();
        private LinkedList<Integer> list = new LinkedList<>();
        private int capacity;
        public test(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)){
                return -1;
            }
            Integer i = map.get(key);
            list.remove((Integer) key);  //⭐注意这里  重载有坑  按照元素删
            list.addFirst(key);
            return i;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)){
                //更新
                map.put(key , value);
                list.remove(key);
                list.addFirst(key);
            }else {  //新增
                map.put(key,value);
                list.addFirst(key);
                if (map.size() > capacity){  //超了就删除
                    map.remove(list.removeLast());
                }
            }
        }
    }
}
