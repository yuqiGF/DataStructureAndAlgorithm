package com.yuqiqi.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * ⭐活动选择问题
 * 每个活动有起始时间和结束时间，让活动时间不重合 选择最多的活动数
 * ⭐贪心   按照活动的结束时间排序
 */
public class ActivityChoose {
    public static class Activity{
        int index;
        int start;
        int end;

        public Activity(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }
    }

    public static void select(Activity[] activities){
        //按照活动结束时间升序排序
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));
        ArrayList<Activity> selected = new ArrayList<>();
        int end = activities[0].end;
        selected.add(activities[0]);
        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= end){
                end = activities[i].end;
                selected.add(activities[i]);
            }
        }
    }
}
