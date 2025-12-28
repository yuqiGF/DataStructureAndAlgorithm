package com.yuqiqi.dataStructure.queue.blockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列   -单锁实现
 */
public class BlockingQueue1<E> implements BlockingQueue<E> {

    //初始化
    private final E[] array;
    private int head;  //头
    private int tail;  //尾
    private int size;  //队中的元素个数

    @SuppressWarnings("all")
    public BlockingQueue1(int capacity){
        array = (E[]) new Object[capacity];
    }

    //⭐锁初始化
    private ReentrantLock lock = new ReentrantLock();
    //⭐条件变量初始化   ⭐offer和poll阻塞的时候需要条件变量来让他们等待
    private Condition headWaits = lock.newCondition(); //⭐condition需要配合lock来使用  队列空 无法取出时 配合poll使用
    private Condition tailWaits = lock.newCondition(); //队列满了 无法加入时 配合offer使用

    boolean isEmpty(){
        return size == 0;
    }

    boolean isFull(){
        return size == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {   //⭐入队  此时唤醒出队的时候等待的线程
        lock.lockInterruptibly(); //⭐上锁  可打断锁   ⭐记得解锁！！！
        try {
            while (isFull()){  //满了  ⭐注意 这里不能用if  会出bug  要用while反复判断是否已满，防止虚假唤醒⭐
                tailWaits.await(); //⭐调用await方法等待  让他去tailWaits这个condition里等着
            }
            array[tail] = e;
            tail ++;
            if (tail == array.length){  //实现循环
                tail = 0;
            }
            size ++;
            headWaits.signal(); //⭐唤醒等待出队的线程  注意 是signal方法
        } finally {
            lock.unlock();  //解锁  ⭐ 最后一定要解锁  一般用try  finally实现
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {  //毫秒
        lock.lockInterruptibly(); //上锁
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);//把毫秒转化成纳秒
            while (isFull()){  //满了  ⭐注意 这里不能用if  会出bug  要用while反复判断是否已满，防止虚假唤醒⭐
                if (t <= 0){
                    return false; //时间到了 放弃
                }
                t = tailWaits.awaitNanos(t); //⭐待有时间上限的等待  最多等待多少纳秒  返回剩余时间（避免虚假唤醒）
            }
            array[tail] = e;
            tail ++;
            if (tail == array.length){  //实现循环
                tail = 0;
            }
            size ++;
            headWaits.signal(); //⭐唤醒等待出队的线程  注意 是signal方法
            return true;
        } finally {
            lock.unlock();  //解锁  ⭐ 最后一定要解锁  一般用try  finally实现
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly(); //上锁
        try {
            while (isEmpty()){
                headWaits.await();  //等待
            }
            E value = array[head];  //取值
            array[head] = null; //帮助gc
            head ++; //head右移
            if (head == array.length){
                head = 0;  //实现循环
            }
            size --;
            tailWaits.signal(); //唤醒入队时等待的线程
            return value;
        } finally {
            lock.unlock();  //解锁
        }
    }
}
