package com.yuqiqi.dataStructure.heap;

/**
 * 大顶堆   在优先级队列的第三实现那里写过  就是一个根节点总是大于左右两节点的完全二叉树
 * 堆完全可以用一个线性的数组来表示  ⭐父节点=（子节点-1）/2 向下取整   左子节点 = 2*父节点+1  右子节点 = 2*父节点 + 2
 * ⭐重点是上浮  下潜  建堆
 *
 * 小顶堆与之同理  只不过是根节点永远小与两个子节点而已  变一下符号稍微改改就行
 */
public class MaxHeap {
    public int[] array;  //初始数组
    public int size;

    public MaxHeap(int capacity){
        array = new int[capacity];
    }

    //带建堆的构造器
    public MaxHeap(int[] array){
        this.array = array;
        size = array.length;
        heapify();  //建堆
    }

    /**
     * ⭐建堆  将一个二叉树变为大顶堆   ⭐弗洛伊德建堆算法  找到最大的非叶子节点 然后将它之前的元素全部做一次下潜
     * 时间复杂度 O（n）
     */
    private void heapify(){
        //（总共有n个节点）找到最后一个节点（也就是索引为size-1的节点）的父节点 就是最后一个非叶子节点 ，带入公式得 ⭐索引 = n/2 - 1；
        for (int i = size/2 - 1; i >= 0 ; i--) {
            down(i); //最大的叶子节点前面的所有节点依次下潜
        }
    }

    /**
     * 删除堆顶元素    ⭐先让堆顶和堆尾呼唤，然后删掉堆尾，堆顶下潜
     * @return
     */
    public int poll(){
        if (isEmpty()){
            return -1; //空的
        }
        int polled = array[0];
        swap(0,size - 1);
        array[size - 1] = 0;
        size --;
        down(0); //下潜
        return polled;
    }

    /**
     * 删除指定索引位置的元素   同样是将其先和堆底元素交换  然后删除堆底，然后交换后的那个位置下潜
     * @param index
     */
    public int poll(int index){
        if (isEmpty() || index >= size){
            return -1; //删除失败
        }
        int polled = array[index];
        swap(index,size - 1);
        array[size - 1] = 0;
        size --;
        down(index); //下潜
        return polled;
    }

    /**
     * 替换堆顶元素
     * @param value
     * @return
     */
    public void replace(int value){
        if (isEmpty()){
            return; //失败  这里可抛异常
        }
        array[0] = value;
        down(0);
    }

    /**
     * 堆尾部添加元素
     */
    public boolean offer(int offered){
        if (isFull()){
            return false; //满的
        }
        up(offered); //堆尾上浮
        size ++;
        return true;
    }

    /**
     * 获取堆顶元素
     */
    public int peek(){
        if (isEmpty()){
            return -1; //空的   这里可以跑一个异常的
        }
        return array[0];
    }

    /**
     * ⭐上浮  将堆尾元素上浮  直到到顶 或者小于父元素
     */
    private void up(int offered){
        int child = size;  //新加入的元素的最初索引位置在size上
        int parent = (child - 1)/2;
        while (child > 0 && offered > array[parent]){   //给新家的“child”找位置
            array[child] = array[parent]; //父亲下来  空出孩子的位置
            child = parent; //更新孩子
            parent = (child - 1)/2; //更新父亲
        }
        array[child] = offered;
    }


    /**
     * 下潜  父节点元素和两个子节点中较大的那个交换  直到没有父节点或者没有比自己大的子节点为止
     * @param parent 待下潜的元素的索引位置
     */
    public void down(int parent){
        int left = parent * 2 + 1;  //套公式计算左右节点索引
        int right = left + 1;
        int max = parent; //先假设最大索引就是父节点parent
        if (left < size && array[max] < array[left]){ //左边比较大
            max = left;
        }
        if (right < size && array[max] < array[right]){
            max = right;
        }
        if (max != parent){  //父节点不是最大了
            swap(parent , max);
            down(max);  //递归下潜
        }
    }

    /**
     * 交换两个位置的元素
     */
    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == array.length;
    }

}
