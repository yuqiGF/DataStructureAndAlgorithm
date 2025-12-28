package com.yuqiqi.dataStructure.queue.simple;

/**
 * 自定义队列接口 （java字带的Queue接口方法太多了，所以重新写一个来实现）  未来要用链表和数组分别实现
 * ⭐队列嘛  尾部插入  头部排出  可以理解为一个只有尾插头删方法的链表
 */
public interface Queue<E> {
    /**
     * 向队列尾部插入值
     * @param value 值
     * @return 是否插入成功
     */
    boolean offer(E value);

    /**
     * 从队列头获取值并移除
     * @return 值或者null
     */
    E poll();

    /**
     * 从头获取值 不移除
     * @return 获取到的值
     */
    E peek();

    /**
     * 判断是否为空
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 判断队列是否已满
     * @return 是否已满
     */
    boolean isFull();
}
