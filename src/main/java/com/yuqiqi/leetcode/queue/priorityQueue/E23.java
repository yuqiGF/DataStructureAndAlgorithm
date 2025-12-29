package com.yuqiqi.leetcode.queue.priorityQueue;

import com.yuqiqi.leetcode.bean.ListNode;

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

    static class MinHeap {
        ListNode[] array;
        int size;

        public MinHeap(int capacity) {
            array = new ListNode[capacity];
        }

        //入堆  往最后面加  小的上浮
        public boolean offer(ListNode value){
            if (isFull()){
                return false;
            }
            int child = size;
            int parent = (size - 1) / 2;
            while(child > 0 && value.val < array[parent].val){
                array[child] = array[parent];
                child = parent;
                parent = (child - 1) / 2;
            }
            array[child] = value;
            size ++;
            return true;
        }

        //交换
        public void swap(int i , int j){
            ListNode temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        //下潜  根 左 右 三个找最小的
        public void down(int parent){
            int left = parent * 2 + 1;
            int right = left + 1;
            int min = parent; //先假设是parent最小
            if (left < size && array[left].val < array[min].val){
                min = left;
            }
            if (right < size && array[right].val < array[min].val){
                min = right;
            }
            if (min != parent){ //最小节点有更新
                swap(min , parent);
                down(min);  //往下递归
            }
        }

        //出堆  首位交换  小的下潜
        public ListNode poll(){
            if (isEmpty()){
                return null;
            }
            ListNode node = array[0];
            swap(0,size - 1);
            array[size - 1] = null;
            size --;
            down(0); //下潜
            return node;
        }

        //取堆顶
        public ListNode peek(){
            if (isEmpty()){
                return null;
            }
            return array[0];
        }

        //判空
        public boolean isEmpty(){
            return size == 0;
        }

        //判满
        public boolean isFull(){
            return size == array.length;
        }
    }
}
