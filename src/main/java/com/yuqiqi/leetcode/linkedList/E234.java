package com.yuqiqi.leetcode.linkedList;

import com.yuqiqi.bean.ListNode;

/**
 * 回文链表   给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */
public class E234 {
    //先倒过来 然后依次比较
    public boolean isPalindrome(ListNode head) {
        ListNode p = head;
        ListNode copy = new ListNode(head.val,null); //复制原链表 避免破环原始链表
        ListNode p0 = copy;
        while(p.next != null){
            p0.next = new ListNode(p.next.val,null);
            p = p.next;
            p0 = p0.next;
        }
        ListNode reverse = reverse(copy);
        ListNode p1 = head;
        ListNode p2 = reverse;
        while(p1 != null){
            if (p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    /**
     * 反转链表
     * @param p
     * @return
     */
    public ListNode reverse(ListNode p){
        if (p == null || p.next == null){
            return p;
        }
        //找最后一个节点  作为反转后的头节点
        ListNode last = reverse(p.next);
        p.next.next = p;  //p是本节点  p.next是上面操作的哪个节点 上面的哪个节点指向本节点
        p.next = null;  //本节点的next更新为null
        return last;
    }


    /**
     * 方法二     可以将快慢指针找中间点和反转链表合在一起  反转前面的 和后面的比
     * ⭐快慢指针找到中间节点
     * ⭐从中间节点开始后面的反转  加入到新的链表
     * ⭐依次比较两个链表
     */
    public boolean isPalindrome2(ListNode head){
        ListNode p1 = head;  //慢指针
        ListNode p2 = head;  //快指针
        ListNode reverse = null;
        if (head == null || head.next == null){
            return true;
        }

        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //找到了中间节点p1 开始反转
        while(p1 != null){
            reverse = new ListNode(p1.val,reverse);  //直接头插反转
            p1 = p1.next;
        }
        //比较
        while(reverse != null){
            if (head.val != reverse.val){
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }
}
