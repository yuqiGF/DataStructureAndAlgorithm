package com.yuqiqi.leetcode;

import java.util.List;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class E206 {

    /**
     * 节点类定义
     */
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
    /**
     * 方法一  直接按照顺序遍历 然后头插
     */
    public ListNode solution1(ListNode head){
        //定义一个要遍历的指针   初始指向head
        ListNode p = head;
        ListNode res = null; //初始化结果链表
        while(p != null){  //开始遍历
            //循环创建节点
            res = new ListNode(p.val, res);  //头插
            p = p.next;
        }
        return res;
    }
}
