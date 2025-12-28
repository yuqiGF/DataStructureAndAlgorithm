package com.yuqiqi.dataStructure.queue.priorityQueue;

import com.yuqiqi.dataStructure.queue.simple.Queue;

/**
 * 优先级队列  - 基于有序数组 实现（默认尾部优先级大）     ⭐数组中的元素按照优先级排好序   插入的时候进行插入排序
 * 方便取元素  但是插入的时候复杂度高
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue2(int capacity){
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        insert(value);
        size ++;
        return true;
    }

    /**
     * 插入元素     ⭐插入并排序  一次插入排序的内部循环
     * @param value
     */
    private void insert(E value) {
        int p = size - 1; //从数组尾部开始遍历
        while(p >= 0 && value.priority() > array[p].priority()){ //当前优先级大于指针位置的化 当前元素后移
            array[p + 1] = array[p];  //指针位置的元素上移
            p --;
        }
        //找到了第一个比指针优先级小的位置  直接插入
        array[p + 1] = value;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        E value = (E) array[size - 1];
        size --;
        array[size] = null; //帮助gc
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return (E) array[size - 1]; //入队的时候已经排好序了
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
