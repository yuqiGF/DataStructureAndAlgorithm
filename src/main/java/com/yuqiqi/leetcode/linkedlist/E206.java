package com.yuqiqi.leetcode.linkedlist;

import com.yuqiqi.leetcode.bean.ListNode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class E206 {
    /**
     * 方法一  直接按照顺序遍历 然后头插
     */
    public ListNode solution1(ListNode head){
        //定义一个要遍历的指针   初始指向head
        ListNode p = head;
        ListNode res = null; //初始化结果链表
        while(p != null){  //开始遍历
            //循环创建节点
            res = new ListNode(p.val, res);  //头插
            p = p.next;
        }
        return res;
    }

    /**
     * 方法二  创建一个新链表list2 从list1移除头节点 放到list2的头节点
     */
    public ListNode solution2(ListNode head){
        return null;
    }

    /**
     * 方法三  递归
     */
    public ListNode solution3(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        //先找到最后一个节点
        ListNode last = solution3(head.next);
        //让最后一个节点指向它的上一个节点  （在“归”下面操作   在归的时候操作 上一个递归循环已经结束了，退回到了外层，此时head指针指向的是相对的外层）
        //此时head.next 指向的是刚才操作的内层 ，现在要让那个内层指向当前外面的这个head节点   （这里head只代表节点 并非头）
        head.next.next = head;
        //为了避免节点循环  先让外侧的这个节点指向null
        head.next = null;
        return last;
    }

    /**
     * 方法四 双节点指针o1  n1    每次取出o1的第二个元素，让它移动到链表的最前面，然后o1后移一位   知道o1的下一个节点是null的时候结束循环
     * ⭐搬o2
     */
    public ListNode solution4(ListNode o1){ //o1始终代表旧链表的头部
        //处理空链表和只有一个节点的链表
        if (o1 == null || o1.next == null){
            return o1;
        }

        ListNode o2 = o1.next;  //始终代表旧链表的第二个节点  更新了要改回来
        ListNode n1 = o1;  //始终代表新链表的头部  更新了要改回来
        while(o2 != null){
            //取出o2
            o1.next = o2.next;
            //o2放到n1的前面
            o2.next = n1;
            //更新n1
            n1 = o2;
            //更新o2
            o2 = o1.next;
        }
        return n1;
    }

    /**
     * 方法5 创建一个新链表list2 从list1移除头节点 放到list2的头节点  （面向过程写法）
     * ⭐搬o1
     */
    public ListNode solution5(ListNode o1){
        ListNode n1 = null;   //新链表的头节点初始为空
        while(o1 != null){
            //o2指向原链表的下一个节点  ⭐用o2记录一下位置
            ListNode o2 = o1.next;
            //搬移o1
            o1.next = n1;
            //更新n1
            n1 = o1;
            //更新o1 此时o2就是旧链表的头节点
            o1 = o2;
        }
        return n1;
    }
}
