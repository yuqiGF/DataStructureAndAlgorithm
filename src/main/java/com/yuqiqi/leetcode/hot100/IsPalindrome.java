package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

import java.util.List;

/**
 * 回文链表
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        //特殊情况
        if (head == null || head.next == null){
            return true;
        }
        //找到中间位置 然后反转 对比
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode rev = null;  //反转后节点的头节点
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        while (p1 != null){
            //头插
            rev = new ListNode(p1.val , rev);
            p1 = p1.next;
        }
        //开始对比
        while (rev != null){
            if (rev.val == head.val){
                rev = rev.next;
                head = head.next;
            }else {
                return false;
            }
        }
        return true;
    }
}
