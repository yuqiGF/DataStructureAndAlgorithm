package com.yuqiqi.datastructure.queue.priorityqueue;

import com.yuqiqi.datastructure.queue.simple.Queue;

/**
 * 优先级队列实现  -无序数组实现    ⭐这里要让泛型类继承一个优先级接口
 * ⭐⭐在泛型中 extends 代表的是指定泛型类的上界   代表类E至少实现了***接口  关注的是类的约束能力  而不是继承语法本身
 * 这里代表泛型E必须实现了优先级接口     只有是实现了priority的类才能填入泛型位置
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {
    //这里的泛型E一定实现了Priority接口 所以定义初始数组的时候需要使用priority数组
    Priority[] array;
    int size;  //因为是无序数组 所以这里只需要size记录一下队尾位置就够了

    public PriorityQueue1(int capacity){
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {  //入队  O（1）
        if (isFull()){
            return false;
        }
        array[size] = value;
        size ++;
        return true;
    }

    /**
     * 返回优先级最高的索引值  通过双指针来实现  不断更新max指针
     * @return 优先级最高的索引
     */
    private int selectMax(){
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()){ //i位置的优先级更大
                max = i;
            }
        }
        return max;
    }

    /**
     * 删除索引位置的元素
     * @param index
     */
    public void remove(int index){  //O（n）
        if (index < size - 1){ //删除的不是最后一个元素
            System.arraycopy(array,index + 1,array,index,size - 1 - index); //把max后面的元素集体前移一位
        }
        array[size - 1] = null; //置空 帮助gc
        size --; //让数组访问不到 就代表删了
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        int max = selectMax(); //最高优先级的索引位置
        E value = (E) array[max];
        remove(max);
        return value;
    }

    @Override
    public E peek() {  //返回优先级最高的元素
        if (isEmpty()){
            return null;
        }
        int max = selectMax();
        return (E) array[max];
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
