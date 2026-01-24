package com.yuqiqi.leetcode.linkedlist;

import com.yuqiqi.leetcode.bean.ListNode;

/**
 * 删除到数第n个节点  ⭐递归计数！
 */
public class E19 {
    /**
     * 方法一： 递归  ⭐通过递归来计数  最内层计为0 然后每次返回的之后+1集即可知道是倒数第几个元素了
     * @param head
     * @param n
     * @return
     */
    public ListNode solution(ListNode head, int n){
        //设置哨兵节点  方便删除第一个元素
        ListNode sentinel = new ListNode(-1,head);
        recursion(sentinel,n);  //执行操作
        return sentinel.next; //返回哨兵的下一个  即为原来的链表的头节点
    }

    /**
     * 递归方法  用于记录倒数第几个元素
     * @param p
     * @param n
     * @return
     */
    private int recursion(ListNode p , int n){
        if (p == null){
            return 0; //结束条件
        }
        int re = recursion(p.next, n); //下一个节点的倒数位置
        //加入判断逻辑  判断的是下一个节点的位置 而不是数值
        if (re == n){
            p.next = p.next.next; //删除操作
        }
        return re + 1;
    }


    /**
     * 方法二： ⭐快慢指针  设置快慢指针p1，p2， 要删除到数第n个，则先让p2向前移动n+1位，然后p1，p2一起移动，
     * 直到p2指向null，p1的位置就是到数第n个节点的上一个节点
     */
    public ListNode solution2(ListNode head , int n){
        //哨兵
        ListNode sentinel = new ListNode(-1,head);
        ListNode p1 = sentinel;
        ListNode p2 = sentinel;
        for (int i = 0; i <= n ; i++) {
            p2 = p2.next;  //p2向后移动n+1位
        }
        while(p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next; //找到了  删除即可
        return sentinel.next;
    }

}
