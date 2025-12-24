package com.yuqiqi.leetcode.linkedList;

import com.yuqiqi.bean.ListNode;

/**
 * ⭐检测链表环的入口 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class E142 {
    /**
     * 弗洛伊德归途赛跑算法   ⭐快慢指针相遇就成环   成环后龟回起点  兔子和龟一起同速度跑，再相遇的地方为环的起点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode p1 = head;  //慢
        ListNode p2 = head;  //快
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){  //相遇了  一定成环
                p1 = head; //龟龟回去
                while(p1 != p2){  //一定会相遇   再次相遇的地方就是环入口
                    p1 = p1.next;
                    p2 = p2.next;  //龟龟和兔子以一样的速度跑
                }
                return p1; //环入口
            }
        }
        return null;
    }
}
