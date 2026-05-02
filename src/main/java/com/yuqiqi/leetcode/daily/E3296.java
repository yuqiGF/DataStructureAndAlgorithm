package com.yuqiqi.leetcode.daily;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个整数 mountainHeight 表示山的高度。
 * 同时给你一个整数数组 workerTimes，表示工人们的工作时间（单位：秒）。
 * 工人们需要 同时 进行工作以 降低 山的高度。对于工人 i :
 * 山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒。例如：
 * 山的高度降低 1，需要 workerTimes[i] 秒。
 * 山的高度降低 2，需要 workerTimes[i] + workerTimes[i] * 2 秒，依此类推。
 * 返回一个整数，表示工人们使山的高度降低到 0 所需的 最少 秒数
 */
public class E3296 {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        //最小堆存三元组  (下一次完成任务的时间点, 当前已完成的高度x, 工人的基础时间w)
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        for (int w : workerTimes) {
            pq.offer(new long[]{(long) w, 1, (long) w});
        }
        long ans = 0;

        for (int i = 0; i < mountainHeight; i++) {
            long[] curr = pq.poll();
            long finish_time = curr[0];
            long x = curr[1];
            long w = curr[2];
            ans = finish_time;  // 记录最后完成时间

            // 这个工人准备接下一个任务
            long next_x = x + 1;
            long next_finish_time = w * next_x * (next_x + 1) / 2;
            pq.offer(new long[]{next_finish_time, next_x, w});
        }

        return ans;
    }
}
