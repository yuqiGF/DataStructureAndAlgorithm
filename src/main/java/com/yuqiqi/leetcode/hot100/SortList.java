package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

import java.util.List;

/**
 * 链表排序  升序   分治+归并
 * 和 合并k个升序链表基本一样 只不过是每个升序链表中仅有一个元素而已。
 */
public class SortList {
    public ListNode sortList(ListNode head) {

        //思路 遍历 然后找位置插入  （超时）
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, null);  //新哨兵
        if (head == null){
            return null;
        }
        ListNode p = head;  //遍历指针
        while (p != null){
            //找位置  记录next位置
            ListNode next = p.next;
            ListNode pre = sentinel;
            while (pre.next != null && pre.next.val < p.val){
                pre = pre.next;
            }
            p.next = pre.next;
            pre.next = p;

            //p归位
            p = next;
        }
        return sentinel.next;
    }

    //⭐正确做法  分治  不断从中间拆  然后合并两个有序链表
    // 快慢指针找中间  然后二路归并
    public ListNode sortList2(ListNode head) {
        // 递归终止条件：链表为空或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 找到链表的中点
        ListNode mid = findMiddle(head);
        ListNode rightHead = mid.next; // 右半部分的头节点
        mid.next = null; // 断开链表

        // 递归排序左半部分和右半部分
        ListNode left = sortList2(head);
        ListNode right = sortList2(rightHead);

        // 合并两个有序链表
        return mergeTwoLists(left, right);
    }

    // 找到链表的中点（快慢指针法）
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // fast 从 head.next 开始，确保 slow 指向中点或左中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // 虚拟头节点
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // 将剩余的链表接上
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }

        return dummy.next;
    }
}
