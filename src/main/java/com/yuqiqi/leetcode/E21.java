package com.yuqiqi.leetcode;

import com.yuqiqi.bean.ListNode;

/**
 * 合并两个有序链表  将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class E21 {
    /**
     * 方法一： 递归   多路递归   比较两个链表    哪个小就返回哪个  但是要记得更新next
     */
    public ListNode solution1(ListNode list1 , ListNode list2){
        //结束条件  一个链表为空了就直接返回另一个
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        if (list1.val < list2.val){  //找到较小的 放在前面  后面继续递归找较小的
            list1.next = solution1(list1.next,list2);
            return list1;
        }else {
            list2.next = solution1(list2.next,list1);
            return list2;
        }
    }

    /**
     * 方法二： 指针法   创建一个新的带哨兵的链表 然后用三个指针分别遍历  比较两个链表中较小的节点连接到新链表上
     */
    public ListNode solution2(ListNode list1 , ListNode list2){
        ListNode sentinel = new ListNode(-1 , null);
        ListNode p = sentinel;
        ListNode p1 = list1;
        ListNode p2 = list2;
        //判空
        if (p1 == null){
            return p2;
        }
        if (p2 == null){
            return p1;
        }
        //遍历
        while(p1 != null && p2 != null){
            if (p1.val < p2.val){
                p.next = p1;
                //移动指针
                p = p.next;
                p1 = p1.next;
            }else {
                p.next = p2;
                //移动指针
                p = p.next;
                p2 = p2.next;
            }
        }
        //判断哪个为null了  直接把另一个连接到结果链表上
        if (p1 == null){
            p.next = p2;
        }else {
            p.next = p1;
        }
        return sentinel.next;
    }
}
