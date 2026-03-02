package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

import java.util.List;

/**
 * 合并两个有序链表
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //哨兵
        ListNode sentinel = new ListNode(-1, null);
        ListNode p = sentinel;
        ListNode p1 = list1;
        ListNode p2 = list2;
        //头插
        while (p1 != null && p2 != null){
            if (p1.val <= p2.val){
                p.next = p1;
                p = p.next;
                p1 = p1.next;  //p1向后
            }else {
                p.next = p2;
                p = p.next;
                p2 = p2.next;  //p2向后
            }
        }
        //处理残余
        if (p1 != null){
            p.next = p1;
        }
        if (p2 != null){
            p.next = p2;
        }
        return sentinel.next;
    }
}
