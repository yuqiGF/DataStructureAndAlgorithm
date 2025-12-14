package com.yuqiqi.algorithm.binarysearch;

/**
 * ⭐手写动态数组
 */
public class DynamicArray {
    private int size = 0; //逻辑大小
    private int capacity = 10; //初始容量
    private int[] array = new int[capacity]; //初始数组

    /**
     * 添加最后一个元素
     */
    public void addLast(int element){
//        array[size] = element;
//        size ++;
        add(element,size);
    }
    /**
     * 向指定位置插入元素
     */
    public void add(int element , int index){
/*        if (index >= 0 && index < size){
            //index位置开始向后移动
            System.arraycopy(array,index,array,index+1,size-index);
            //在index位置插入元素
            array[index] = element;
            size ++;
        } else if (index == size) {  //最后一个元素
            array[size] = element;
            size ++;
        } else {
            throw new RuntimeException("索引越界");
        }*/
        //⭐优化一下下逻辑
        if (index < 0 || index > size){
            throw new RuntimeException("索引越界");
        }
        if (index < size){
            //index位置开始向后移动
            System.arraycopy(array,index,array,index+1,size-index);
        }
        array[index] = element;
        size ++;
    }
}
