package com.yuqiqi.leetcode.ds.linkedlist;

import com.yuqiqi.leetcode.ds.bean.ListNode;

/**
 * 查找链表的中间节点  给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class E876 {
    /**
     * 快慢指针   一个指针依次走一步  另一个一次走两步  第二个指针走到头后（自己为null或者自己的下一个节点为null） 第一个指针就指向了中间
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null && p2.next != null){  //结束条件 p2指向null或者p2的下一个节点指向null
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

}
