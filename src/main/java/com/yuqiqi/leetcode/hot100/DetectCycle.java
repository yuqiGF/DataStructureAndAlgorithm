package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

/**
 * 环的入口
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        //弗洛伊德成环算法
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                //成环了
                p1 = head;  //⭐慢指针回去
                while (p1 != p2){
                    p1 = p1.next;  //然后快指针变速⭐
                    p2 = p2.next;
                }
                return p1;  //相遇的节点就是入口
            }
        }
        return null;
    }
}
