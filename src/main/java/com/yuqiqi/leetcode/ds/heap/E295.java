package com.yuqiqi.leetcode.ds.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ⭐⭐求数据流的中位数   思路 把数据分成两个部分  较小的与部分和较大的一部分
 * 规定中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 *
 * 创建两个堆  左边大顶堆 右边小顶堆 ，然后依次往两个堆里面添加元素， 这样大小不相等时元素多的堆的堆顶是中位数，大小相等时两个堆的堆顶都是中位数
 * ⭐为了保证堆的平衡  当左右堆的元素个数一样的时候  加到左堆  当左右堆元素个数不一样的时候加到右堆
 * 技巧：
 * ⭐当左堆（大顶堆）加入元素的时候  要是加一个比堆顶还大的元素那我不就炸了（堆顶不是中位数了），所以 先把新元素加入到右堆 ，再弹出右堆中最小的加入到左堆   （右边最小的加入到左边 一定时左变最大的  所以依然是中位数）
 * ⭐当右堆加入元素时，先把新元素加入到左堆，再弹出左堆中最大的元素到右堆
 * 就相当于和另一个堆中的元素做了一个比较，取另一个堆中最大的或者最小的来保证堆顶是中位数
 */
public class E295 {
    //JDK中自带的优先级队列 默认是小顶堆  给他反转一下就能变成大顶堆
    private PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    //小顶堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    public E295() {

    }

    public void addNum(int num) {
        if (left.size() == right.size()){  //左右两个堆的大小相同
            //往左堆里加   （加的时候先加到右堆 然后取右堆中最小的（堆顶）加回来）
            right.offer(num);
            Integer min = right.poll();
            left.offer(min);
        }else{
            //往右堆里加  先加到左堆
            left.offer(num);
            Integer max = left.poll();
            right.offer(max);
        }
    }

    public double findMedian() {
        //如果两个堆的元素个数不相同的话 左堆一定更多 而且堆顶为中位数
        if (left.size() != right.size()){
            return left.peek();
        }else { //元素个数相同的话求中间两个数的平均值
            return (double) (left.peek() + right.peek()) /2;
        }
    }
}
