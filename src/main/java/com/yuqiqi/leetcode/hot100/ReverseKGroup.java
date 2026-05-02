package com.yuqiqi.leetcode.hot100;

import com.yuqiqi.leetcode.ds.bean.ListNode;

/**
 * K个一组反转链表   反转的时候把当前的断开  倒腾四个指针
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode pre = sentinel;  //交换开始的上一个节点的位置
        ListNode end = sentinel;   //交换结束的位置

        while (end.next != null){  //循环条件  后面还有东西
            for (int i = 0; i < k && end != null; i++) {  //注意循环条件⭐
                end = end.next; //后移k位
            }
            if (end == null){
                break;  //遍历后发现不够数量 跳出即可
            }

            ListNode start = pre.next;  //开始⭐
            ListNode next = end.next;  //记录下一个开始
            //⭐四个指针找齐了 开始截断
            end.next = null;
            //开始反转
            pre.next = reverse(start);  //把前面接上
            start.next = next;  //把后面接上
            //复位⭐
            pre = start;
            end = pre;
        }
        return sentinel.next;
    }

    /**
     * 局部反转 （end处断开后） 正常反转链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        //找最后一个节点
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
