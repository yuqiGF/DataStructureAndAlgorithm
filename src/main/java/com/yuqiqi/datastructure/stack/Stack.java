package com.yuqiqi.datastructure.stack;

/**
 * 栈  只能在一端添加或者删除元素
 */
public interface Stack<E> {
    /**
     * 压栈  向栈顶压入元素
     * @param value 待压元素
     * @return 是否成功
     */
    boolean push(E value);

    /**
     * 从栈顶弹出元素
     * @return 弹出的元素或者null
     */
    E pop();

    /**
     * 取出栈顶元素  不弹出
     * @return 栈顶元素 或者null
     */
    E peek();

    /**
     * 判断是否为空
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 是否已满
     * @return 是否已满
     */
    boolean isFull();
}
