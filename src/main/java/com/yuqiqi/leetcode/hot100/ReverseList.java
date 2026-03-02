package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

/**
 * 反转链表
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        //递归
        if (head == null || head.next == null){
            return head;
        }
        //找最后一个节点
        ListNode last = reverseList(head.next);
        head.next.next = head;  //内层的下一个指向外层
        head.next = null;
        return last;
    }
}
