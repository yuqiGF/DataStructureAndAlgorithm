package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

/**
 * 两数相加
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(-1, null);  //哨兵节点
        ListNode p = sentinel;  //指针
        boolean flag = false;  //进位
        while (l1 != null && l2 != null){
            int num;
            if (!flag){  //没进位
                num = l1.val + l2.val;
            }else {
                num = l1.val + l2.val + 1;
            }
            if (num >= 10){
                flag = true;
                //尾插
                p.next = new ListNode(num % 10 , null);
            }else {
                flag = false;
                p.next = new ListNode(num , null);
            }
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null){
            int num;
            if (flag){
                num = 1 + l1.val;
            }else {
                num = l1.val;
            }
            if (num >= 10){
                flag = true;
                //尾插
                p.next = new ListNode(num % 10 , null);
            }else {
                flag = false;
                p.next = new ListNode(num , null);
            }
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null){
            int num;
            if (flag){
                num = 1 + l2.val;
            }else {
                num = l2.val;
            }
            if (num >= 10){
                flag = true;
                //尾插
                p.next = new ListNode(num % 10 , null);
            }else {
                flag = false;
                p.next = new ListNode(num , null);
            }
            p = p.next;
            l2 = l2.next;
        }
        //最后的进位判断
        if (flag){
            p.next = new ListNode(1 , null);
        }
        return sentinel.next;
    }
}
