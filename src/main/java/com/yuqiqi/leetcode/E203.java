package com.yuqiqi.leetcode;

import com.yuqiqi.bean.ListNode;

/**
 * 移除链表中的元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class E203 {
    /**
     * 方法一： 带哨兵节点的双指针法
     * @param head
     * @param val
     * @return
     */
    public ListNode solution1(ListNode head, int val){
        if (head == null || head.next == null){
            return head;
        }
        ListNode sentinel = new ListNode(-1,head); //哨兵节点
        ListNode p1 = sentinel;
        ListNode p2 = sentinel.next; //两个指针  这个是靠前的
        //开始遍历
        while(p2 != null){
            if (p2.val == val){  //找到了相等的  删掉
                p1.next = p2.next;
                p2 = p1.next; //更新p2
            }else { //没找到 两个指针都后移
                p1 = p2;
                p2 = p2.next;
            }
        }
        return sentinel.next; //返回头节点
    }
}
