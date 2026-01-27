package com.yuqiqi.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 无重叠区间  ⭐贪心 最先结束的活动
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
 */
public class E435 {
    /**
     * 局部最优解  贪心解法  ⭐优先选择最先结束的活动   （可以创建对象  但是这样的消耗太大）
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));  //按照结束时间排序

        int count = 0;
        int end = intervals[0][1];  //第一个的结束时间
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] >= end){ //下一场活动的开始时间大于上一场的结束
                end = interval[1];
            }
            else {
                count ++;
            }
        }
        return count;
    }
}
