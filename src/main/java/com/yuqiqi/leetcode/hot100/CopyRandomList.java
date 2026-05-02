package com.yuqiqi.leetcode.hot100;


import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 随机链表复制  （就是带random的深拷贝）
 */
public class CopyRandomList {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        //难点在于random也是node⭐ 类似于缓存 先暂存以下
        // 把创建好的Node先暂存到map中（key为原节点 value为索引） 同时把新节点暂存到list中 方便取出
        // 第二次遍历的时候取出
        Node p = head;
        Node sentinel = new Node(-1);
        Node n = sentinel;
        ArrayList<Node> nodeList = new ArrayList<>();  //节点列表
        HashMap<Node, Integer> map = new HashMap<>();  //索引map  用来从list中找节点
        int k = 0;  //记录索引位置
        while (p != null){
            Node newNode = new Node(p.val);
            n.next = newNode;
            n = n.next;
            //记录
            nodeList.add(newNode);
            map.put(p , k);   //⭐map仅起到记录索引的作用
            k ++;
            p = p.next;
        }
        //第二次遍历
        p = head;
        n = sentinel.next;
        while (p != null){
            if (p.random == null){
                n.random = null;
            }else {
                Node random = p.random;
                //从map中取出该节点应该对应的索引位置
                Integer index = map.get(random);
                n.random = nodeList.get(index);
            }
            p = p.next;
            n = n.next;
        }
        return sentinel.next;
    }
}
