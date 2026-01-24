package com.yuqiqi.datastructure.queue.blockingqueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列   双锁实现   入队和出队各需要一把锁，且各自有各自的condition
 * ⭐加两个锁后size变为共享资源 可能发生指令交错  需要使用原子类解决
 * ⭐注意每个condition需要与之对应的锁的存在，在唤醒的时候要先上锁
 * ⭐为了避免死锁，要在与原有锁平级的地方加锁来唤醒其他线程
 * ⭐为了提高性能，采用级联唤醒，
 * -入队时从0到1才需要唤醒等待的poll线程，其他poll线程由该唤醒的poll线程决定要不要唤醒（当取之前剩余的个数大于1时）
 * -出对是从满到不满才需要唤醒等待的offer线程 ，其他线程要由该offer线程决定（剩余空位大于1时）
 * @param <E>
 */
public class BlockingQueue2<E> implements BlockingQueue<E> {
    //初始化
    private final E[] array;
    private int head;  //头
    private int tail;  //尾
    //CAS  Compare And Swap 比较并交换   比较此时内存中的数据是否和自己一致
    private AtomicInteger size = new AtomicInteger();  //队中的元素个数 ⭐共享资源  使用原子类⭐

    @SuppressWarnings("all")
    public BlockingQueue2(int capacity){
        array = (E[]) new Object[capacity];
    }

    //⭐锁初始化  两把锁
    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition(); //⭐condition需要配合lock来使用 队列空 无法取出时 配合poll
    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition(); //队列满了 无法加入时 配合offer使用

    boolean isEmpty(){
        return size.get() == 0;
    }

    boolean isFull(){
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c; //记录新增前的元素个数
        try{
            while (isFull()){
                tailWaits.await(); //队列满 等待
            }
            array[tail] = e;
            tail ++;
            if (tail == array.length){
                tail = 0;
            }
//            size ++;  //⭐size底层分成了三步  是共享资源  可能被另一把锁修改  线程不安全 会产出指令交错
            c = size.getAndIncrement(); //原子类自增 相当于size++  不会产生指令交错   先获取后新增

            if (array.length - c > 1){  //还有不止一个空缺的时候唤醒其他offer线程
                headWaits.signal();
            }
/*            // 唤醒等待的poll线程  ⭐注意要使用配对的锁    ⭐ 会产生死锁   ⭐解决方式： 把两把锁设置成平级的 而不是嵌套的
            headLock.lock();
            try{
                headWaits.signal();  //先要有对应的headlock才能使用headlock的condition
            }finally {
                headLock.unlock();
            }*/
        }finally {
            tailLock.unlock();  //解锁
        }
        //⭐ 避免死锁地唤醒poll线程
        //⭐ 使用级联唤醒提高性能
        if (c == 0){  //从0变到非空的时候才执行唤醒   其他的唤醒交给唤醒的这个线程去唤醒
            headLock.lock();
            try{
                headWaits.signal();  //先要有对应的headlock才能使用headlock的condition
            }finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E value; //把变量定义到外面来返回
        int c; //取走前的元素个数
        headLock.lockInterruptibly();//上锁
        try {
            while (isEmpty()){
                headWaits.await(); //队列空  等待
            }
            value = array[head];
            array[head] = null;
            head ++;
            if (head == array.length){
                head = 0;
            }
//            size --;
            c = size.getAndDecrement();//自减   原子操作

            if (c > 1){ //有多余的元素  不止一个
                headWaits.signal(); //⭐级联唤醒
            }

/*            //唤醒等待不满的offer线程
            tailLock.lock();
            try{
                tailWaits.signal();
            }finally {
                tailLock.unlock();
            }*/
        } finally {
            headLock.unlock();
        }

        //⭐避免死锁地唤醒等待不满的offer线程
        if (c == array.length){  //从满减到不满才唤醒offer线程  其他的offer线程由offer级联唤醒
            tailLock.lock();
            try{
                tailWaits.signal();

            }finally {
                tailLock.unlock();
            }
        }
        return value;
    }
}
