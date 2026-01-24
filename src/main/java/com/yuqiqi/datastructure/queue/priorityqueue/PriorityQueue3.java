package com.yuqiqi.datastructure.queue.priorityqueue;

import com.yuqiqi.datastructure.queue.simple.Queue;

/**
 * ⭐大顶堆实现优先级队列     大顶堆：根节点总是大于子节点的完全二叉树    父找子：左2i+1 右2i+2  子找父（i - 1）/2 向下取整
 * 虽然是非线性的数据结构  但是可以用线性的数组去存   加的时候要从左到右依次填满
 */
public class PriorityQueue3<E extends Priority> implements Queue<E> {
    //初始化数组
    Priority[] array;
    int size;

    //构造器 指定数组容量
    public PriorityQueue3(int capacity){
        array = new Priority[capacity];
    }

    /**
     * ⭐入队新元素 加入到数组末尾（索引位置child）  然后⭐<b>上浮</b>⭐
     * 不断比较新加元素与其父亲节点（parent）的优先级， 如果父亲的优先级低，则父亲下移，并找下一个父亲   直到父亲优先级更高或者child为0 成为堆顶
     * ↑使之恢复为大顶堆
     * @param value 值
     * @return 是否成功
     */
    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false; //满了
        }
        int child = size;
        size ++; //加了元素  size记得自增
        int parent = (child - 1) / 2;  //⭐ 这是大顶堆找父节点的公式  记住⭐
        while(child > 0 && value.priority() > array[parent].priority()){
            array[child] = array[parent]; //父节点下移
            child = parent ;  //子节点的位置变更到父节点处
            parent = (child - 1) / 2; //更新parent
        }
        //找到了该去的位置
        array[child] = value; //入堆
        return true;
    }

    /**
     * 辅助方法  交换元素
     * @param i
     * @param j
     */
    public void swap(int i , int j){
        E temp = (E) array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 元素下潜   递归（当然也可以用while parent < left 实现 更新元素实现）
     * @param parent 父节点索引
     */
    public void down(int parent){
        int left = parent * 2 + 1;
        int right = left + 1;
        //寻找根，左，右三个节点中优先级较大的那个 ⭐大顶堆不保证左右子节点的大小关系
        int max = parent; //先假设parent优先级最高  后面按需更新
        if (left < size && array[max].priority() < array[left].priority()){
            max = left;
        }
        if (right < size && array[max].priority() < array[right].priority()){
            max = right;
        }
        if (max != parent){  //如果发生了变更  parent不再是最大  ⭐这里也充当了递归的出口
            swap(max,parent); //交换
            down(max); //继续以最大优先级的为基准下潜
        }
    }

    /**
     * ⭐出队元素  先让堆顶元素和堆尾元素交换位置，然后size-- 删除元素  然后堆顶⭐<b>下潜<b/>⭐
     * （下潜）：从堆顶开始，父元素和子元素中较大的交换  直到父元素大于两个子元素或者没有孩子为止
     * @return 出队的元素
     */
    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        E polled = (E) array[0]; //暂存
        swap(0,size - 1); //顶和底交换
        array[size - 1] = null; //帮助gc
        size --; //移除元素
        //⭐下潜
        down(0);
        return polled;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return (E) array[0];
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
