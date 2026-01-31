package com.yuqiqi.leetcode.ds.linkedlist;

import com.yuqiqi.leetcode.ds.bean.ListNode;

/**
 * 链表去重 ⭐但是只返回不重复的元素 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class E82 {
    /**
     * 方法一   三指针带哨兵   但是找的是相等的元素后一直往后找  直到不等为止
     */
    public ListNode solution1(ListNode head){
        if (head == null || head.next == null){
            return head;
        }

        ListNode sentinel = new ListNode(-1,head);
        ListNode p0 = sentinel; //删除节点的时候使用
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p2 != null){
            if (p1.val == p2.val){
                while(p2 != null && p2.val == p1.val){
                    p2 = p2.next;  //p2移动到与p1不等的为止为止
                }
                p0.next = p2;  //删除
                //更新其他指针的位置
                if (p2 != null){
                    p1 = p2;
                    p2 = p2.next;
                }
            }else {
                p0 = p0.next;
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return sentinel.next;
    }

    /**
     * 方法二 递归  返回去重后的链表   找到了有重复的后要一直找到不重复的为止  则以它的下一个继续递归  没找到就返回自己 并且更新next节点
     */
    public ListNode solution2(ListNode p){
        if (p == null || p.next == null){
            return p;   //递归结束条件
        }
        if (p.val == p.next.val){  //找到重复的了
            ListNode x = p.next.next; // 继续往后找
            while(x != null && x.val == p.val){
                x = x.next;  //直到找到值不一样的
            }
            return solution2(x);
        }
        else {
            p.next = solution2(p.next);  //更新next  继续去重
            return p;
        }
    }

}
