package com.yuqiqi.leetcode.design;

import java.util.HashMap;

/**
 * LFU缓存  最不经常用缓存
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 实现 LFUCache 类：
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class E460_LFU {
    //思路 两张哈希表+双向链表   ⭐一张哈希表存频次  一张哈希表存键     ⭐存频次的哈希表下面链上双向链表
    class LFUCache {

        //节点类
        static class Node {
            Node prev;
            Node next;
            int key;
            int value;
            int freq = 1;  //频度

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        //⭐带哨兵的双向链表 （手写一个）
        static class DoublyLinkedList {
            Node head;  //头哨兵
            Node tail;  //尾哨兵
            int size;  //记录大小

            public DoublyLinkedList() {
                head = new Node();
                tail = new Node();  //哨兵
                head.next = tail;
                tail.prev = head;
            }

            //头部添加  O（1）
            public void addFirst(Node newFirst) {
                Node oldFirst = head.next;
                head.next = newFirst;
                oldFirst.prev = newFirst;
                newFirst.next = oldFirst;
                newFirst.prev = head;
                size++;
            }

            //尾部删除
            public Node removeLast() {
                Node last = tail.prev;
                remove(last);
                return last;
            }

            //已知节点的删除（用于更新）  ⭐传入map中传来的node 不用全表扫描了⭐
            public void remove(Node node) {
                Node prev = node.prev;
                Node next = node.next;
                prev.next = next;
                next.prev = prev;
                size--;
            }

            public boolean isEmpty() {
                return size == 0;
            }
        }

        //初始数据结构  两个哈希表  一个存键值   一个存 频度和添加顺序的双向链表  （每个频度对应一个双向链表）
        private HashMap<Integer, Node> kvMap = new HashMap<>();
        private HashMap<Integer, DoublyLinkedList> freqMap = new HashMap<>();
        private int capacity;  //容量
        private int minFreq = 1;  //初始频度

        public LFUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            //取元素   k不存在  返回-1  k存在 返回值 并且频度+ 1 然后移动到+1频度的双向链表中
            if (!kvMap.containsKey(key)) {
                return -1;
            }
            Node node = kvMap.get(key);
            freqMap.get(node.freq).remove(node);  //根据频度从频度表中找到这个节点所在的链表 移除该节点
            if (node.freq == minFreq && freqMap.get(node.freq).isEmpty()) {
                minFreq++;  //⭐维护最小频度
            }
            node.freq++;  //频度+1
            //没有的话  新建
            if (freqMap.get(node.freq) == null) {
                freqMap.put(node.freq, new DoublyLinkedList());
            }
            freqMap.get(node.freq).addFirst(node);  //放入+1频度链表的头部
            return node.value;
        }

        public void put(int key, int value) {
            //放入元素  存在的话 覆盖   不存在的话 新增  满了的话淘汰
            if (kvMap.containsKey(key)) {
                Node node = kvMap.get(key);
                node.value = value;
                //更新频度
                freqMap.get(node.freq).remove(node);
                if (node.freq == minFreq && freqMap.get(node.freq).isEmpty()) {
                    minFreq++;
                }
                node.freq++;
                if (freqMap.get(node.freq) == null) {
                    freqMap.put(node.freq, new DoublyLinkedList());
                }
                freqMap.get(node.freq).addFirst(node);  //放入+1频度链表的头部
            }
            //新增
            else {
                if (kvMap.size() == capacity) {  //满了  淘汰使用频次最低的一个  一样的话淘汰最先操作的那个
                    //已经维护了最小频度
                    Node removed = freqMap.get(minFreq).removeLast();
                    kvMap.remove(removed.key);
                }

                Node node = new Node(key, value);
                kvMap.put(key, node);
                //更新频度
                if (freqMap.get(node.freq) == null) {
                    freqMap.put(node.freq, new DoublyLinkedList());
                }
                freqMap.get(node.freq).addFirst(node);
                //重置最小频度
                minFreq = 1;
            }
        }
    }
}
