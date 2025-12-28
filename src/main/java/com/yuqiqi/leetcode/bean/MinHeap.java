package com.yuqiqi.leetcode.bean;

import java.util.List;

/**
 * 小顶堆  和优先级队列3中的大顶堆实现类似
 */
public class MinHeap {
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
