package com.yuqiqi.leetcode.ds.queue.monotonicqueue;

import com.yuqiqi.datastructure.queue.monotonicqueue.MonotonicQueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值  ⭐用单调队列
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 */
public class E239 {
    //拿大顶堆来试一下   （无法解决窗口只有一个长度的情况） 没办法找到堆尾的元素
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //大顶堆 （有各种各样的问题  最好别用  好好用单调队列就行了）
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            result[i - k] = heap.peek();
            heap.offer(nums[i]);
            if (heap.peek() == nums[i - k]){
                heap.poll();  //删掉多余的
            }
        }
        result[nums.length - k] = heap.peek();  //最后再peek一下
        return result;
    }

    //⭐单调队列   单调递减队列  队首一定是最大的元素  每次往里面加大的元素的话就把小的元素挤出去了  而小的可以顺延下去
    public int[] maxSlidingWindow2(int[] nums , int k){
        MonotonicQueue deque = new MonotonicQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            deque.offer(nums[i]);
            if (i >= k && deque.peek() == nums[i - k]){
                deque.poll();  //⭐最大元素跑到滑动窗口外了 最大值过期了 移除即可 （不能用队列的大小来安排那段  因为可能有队列没满，最大值也过期了的情况）
            }
            if(i >= k - 1){
                result[i - (k - 1)] = deque.peek();
            }
        }
        result[nums.length - k] = deque.peek();  //最后再peek一下
        return result;
    }

    /**
     * 单调递减队列
     */
    public class MonotonicQueue {
        //拿原本的双端队列进行一下改造
        private LinkedList<Integer> deque = new LinkedList<Integer>();

        public Integer peek(){
            return deque.peekFirst();  //取出第一个
        }

        public void poll(){
            deque.pollFirst();  //出队第一个
        }

        //假装是一个单调减队列 ⭐就这里有点点不一样
        public void offer(Integer t){
            while (!deque.isEmpty() && t > deque.peekLast()){
                deque.pollLast();  //队尾移除
            }
            deque.offerLast(t);  //队尾添加
        }
    }
}
