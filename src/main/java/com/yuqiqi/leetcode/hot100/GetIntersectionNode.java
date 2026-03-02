package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 相交链表
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //遍历存哈希表呗
        HashSet<ListNode> set = new HashSet<>();
        //把a全放进去
        while (headA != null){
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (set.contains(headB)){
                return headB;
            }else {
                headB = headB.next;
            }
        }
        return null;
    }

    //解法2 走到末尾时走其他人的路   总长度都是一样的 相交的话在相交节点相遇 不相交的话会在空节点相遇
    public ListNode test(ListNode A , ListNode B){
        ListNode p = A;
        ListNode q = B;
        while (p != q){
            p = p == null ? B : p.next;
            q = q == null ? A : q.next;
        }
        return p;
    }
}
