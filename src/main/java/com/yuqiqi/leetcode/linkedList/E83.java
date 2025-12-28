package com.yuqiqi.leetcode.linkedList;

import com.yuqiqi.leetcode.bean.ListNode;

/**
 * 删除链表中的重复元素
 */
public class E83 {
    /**
     * 解法一： 双指针带哨兵
     * @param head
     * @return
     */
    public ListNode solution1(ListNode head){
        ListNode sentinel = new ListNode(-1,head); //哨兵
        ListNode p1 = sentinel;
        ListNode p2 = sentinel.next;
        while(p2 != null){
            if (p1.val == p2.val){  //找到了重复元素
                p1.next = p2.next; //删除p2
                p2 = p2.next;
            }else { //没找到
                p1 = p1.next;
                p2 = p2.next; //向后移动一位
            }
        }
        return sentinel.next;
    }

    /**
     * 方法二： 递归 （每次返回的内容是从当前节点开始，完成去重后的内容） 当前节点与下一个节点重复的时候返回下一个节点  不重复了返回自己 但是next更新
     */
    public ListNode solution2(ListNode p){
        if (p == null || p.next == null){
            return null;
        }
        if (p.val == p.next.val){
            return solution2(p.next);  //相等了 留后一个
        }else {
            p.next = solution2(p.next);
            return p;  //不相等  返回更新后的自己
        }
    }
}
