package com.yuqiqi.leetcode.linkedList;

import com.yuqiqi.leetcode.bean.ListNode;

/**
 * ⭐链表成环
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 */
public class E141 {
    /**
     * 弗洛伊德龟兔赛跑算法   快慢指针相遇则成环，  成环后龟返回，兔和龟匀速，再次相遇即为环的入口
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null && p2.next != null){  //兔子没走到终点
            p1 = p1.next;
            p2 = p2.next.next; //先走  然后再判断
            if (p1 == p2){
                return true;
            }
        }
        return false;
    }
}
