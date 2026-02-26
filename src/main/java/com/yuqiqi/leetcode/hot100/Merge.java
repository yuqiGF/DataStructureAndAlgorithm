package com.yuqiqi.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge {
    public static int[][] merge(int[][] intervals) {
        //思路 贪心  ⭐按照左端点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            int m = result.size();
            if (m > 0 && result.get(m - 1)[1] >= interval[0]){
                result.get(m - 1)[1] = Math.max(result.get(m - 1)[1] , interval[1]);  //更新右端点
            }else {
                result.add(interval);
            }
        }

        return result.toArray(new int[0][]);

    }

    public static void main(String[] args) {

    }
}
