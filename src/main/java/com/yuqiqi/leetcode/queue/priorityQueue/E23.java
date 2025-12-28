package com.yuqiqi.leetcode.queue.priorityQueue;

import com.yuqiqi.leetcode.bean.ListNode;
import com.yuqiqi.leetcode.bean.MinHeap;

/**
 * 合并多个有序链表   小顶堆实现  堆容量为链表个数  每次从每个链表中取出头，放入小顶堆 然后取出堆顶元素，并且加入取出元素的下一个元素
 */
public class E23 {
    public ListNode mergeKLists(ListNode[] lists){
        //创建小顶堆
        MinHeap minHeap = new MinHeap(lists.length);
        //创建结果链表哨兵
        ListNode sentinel = new ListNode(-1, null);
        ListNode p = sentinel; //遍历指针

        for (ListNode list : lists) {
            if (list != null){
                minHeap.offer(list); //放入每个list
            }
        }
        while(!minHeap.isEmpty()){
            //取堆顶
            ListNode min = minHeap.poll();
            //尾插
//            p.next = new ListNode(min.val,null);  也可以
            p.next = min;
            p = p.next;
            //加入下一个元素
            if (min.next != null){
                minHeap.offer(min.next);
            }
        }
        return sentinel.next;
    }
}
