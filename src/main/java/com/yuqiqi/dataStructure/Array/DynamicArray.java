package com.yuqiqi.dataStructure.Array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * ⭐手写动态数组
 */
public class DynamicArray implements Iterable<Integer> {
    private int size = 0; //逻辑大小
    private int capacity = 10; //初始容量
    private int[] array = {}; //初始数组  空的  节约内存  懒惰式初始化

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

        //数组扩容
        checkAndGrow();

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

    /**
     * 扩容机制
     */
    private void checkAndGrow() {
        //检查是不是第一个元素
        if (size == 0){
            array = new int[capacity];
        }
        //扩容给机制  1.5倍
        else if (size == capacity){
            //不能够直接用小数加整数  所以还是用移位操作（防止超过有符号位数的上限） （扩大1.5倍）
            capacity = capacity + capacity >> 1;  //新容量
            int[] newArray = new int[capacity];  //新数组
            System.arraycopy(array,0,newArray,0,size); //性能最好的复制
            array = newArray;
        }
    }

    /**
     * 获取元素
     */
    public int get(int index){
        return array[index];
    }

    /**
     * 遍历元素1  forEach遍历   传入一个单方法无返回值的函数式接口Consumer  外部调用的时候重写里面的方法即可
     *  外面调用的时候使用java8的新特性lambda表达式重写   如 ***.forEach（m ->{ System.out.println(m) }）
     */
    public void myForEach(Consumer<Integer> consumer){
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);  //接下来外面得重写这个方法实现自己的功能
        }
    }

    /**
     * 遍历元素2  迭代器遍历   增强型for循环的底层原理   实现iterable接口
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0; //初始化内部元素
            @Override
            public boolean hasNext() {
                return i < size; //是否有下一个元素
            }

            @Override
            public Integer next() {
                return array[i++]; //返回当前元素并将迭代器移动到下一个位置
            }
        };
    }

    /**
     * 遍历元素3 stream流
     */
    public IntStream stream(){
        return IntStream.of(Arrays.copyOfRange(array,0,size)); //转化为intStream流，然后在外面调用流的forEach方法遍历
        //用Arrays。copyOfRange方法将数组截断，不然会直接将无效值一起返回
    }

    /**
     * 删除
     */
    public int remove(int index){  //假设索引范围有效
        //找到被删除的元素
        int removed = array[index];
        if (index < size) {
            //将索引位置之后的元素往前移动
            System.arraycopy(array,index+1,array,index,size-index-1);
        }
        //逻辑大小左移
        size --;  //删最后一个元素的话就直接移动位置即可
        //返回删掉的元素
        return removed;
    }
}
