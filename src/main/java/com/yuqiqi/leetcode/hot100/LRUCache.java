package com.yuqiqi.leetcode.hot100;

import java.util.HashMap;

/**
 * 双向链表+哈希  每次更新双向链表  类似于队列
 */
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
