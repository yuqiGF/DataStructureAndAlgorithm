package com.yuqiqi.datastructure.queue.blockingqueue;

/**
 * ⭐阻塞队列    一般队列在多线程的情况下回产出执行顺序交错的问题   现在就要给他加锁了（synchronized关键字或ReentrantLock对象 ->JUC⭐）
 * 此时满了的时候不用返回false了  而是需要等待（通过条件变量对象）  等到有空位的时候再入队
 * ⭐ 加锁保证线程安全  用条件变量让offer和poll的时候线程进入阻塞状态，↑
 */
public interface BlockingQueue<E> {
    /**
     * 入队
     * @param e 入队的元素
     * @throws InterruptedException 线程执行的时候被打断
     */
    void offer(E e) throws InterruptedException;

    /**
     * 入队
     * @param e 入队的元素
     * @param timeout 超时时间
     * @return 是否成功
     * @throws InterruptedException 线程执行的时候被打断
     */
    boolean offer(E e , long timeout) throws InterruptedException;

    /**
     * 出队
     * @return 出队的元素
     * @throws InterruptedException 线程执行时被打断
     */
    E poll() throws InterruptedException;
}
