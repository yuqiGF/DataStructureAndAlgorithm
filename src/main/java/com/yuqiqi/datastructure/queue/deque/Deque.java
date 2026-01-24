package com.yuqiqi.datastructure.queue.deque;

/**
 * 双端队列    两边都能进出的队列
 */
public interface Deque<E> {
    /**
     * 向对了头部添加元素
     * @param e
     * @return
     */
    boolean offerFirst(E e);

    /**
     * 向队列尾部添加元素
     * @param e
     * @return
     */
    boolean offerLast(E e);

    /**
     * 头部出队
     * @return
     */
    E pollFirst();

    /**
     * 尾部出队
     * @return
     */
    E pollLast();

    /**
     * 获取队首元素
     * @return
     */
    E peekFirst();

    /**
     * 获取队尾元素
     * @return
     */
    E peekLast();

    /**
     * 判空
     * @return
     */
    boolean isEmpty();

    /**
     * 判满
     * @return
     */
    boolean isFull();
}
