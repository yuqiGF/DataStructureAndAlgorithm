package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;
import org.w3c.dom.Node;

import java.util.List;

/**
 * 交换链表中的两个节点  只允许进行节点交换
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode(-1 , head);
        ListNode p = head; //左
        if (head == null){
            return head;
        }
        ListNode q = head.next; //右
        ListNode record = sentinel;  //记录节点  记录本次交换的上一个节点⭐
        while (p != null && q != null){
            //交换
            p.next = q.next;
            q.next = p;
            record.next = q;
            record = p;

            //移动
            p = p.next;
            if (p != null){
                q = p.next;
            }
        }
        return sentinel.next;
    }
}
