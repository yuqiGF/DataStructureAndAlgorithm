package com.yuqiqi;

public class Test2 {
    public long GetMinCalculateCount(long sourceX, long sourceY, long targetX, long targetY) {
        // write code here
        int i = 0;
        while (targetX / 2 >= sourceX){
            i ++;
            targetX = targetX / 2;
        }
        while (targetX - 1 > sourceX){
            i ++;
            targetX --;
        }

        int j = 0;
        while (targetY / 2 >= sourceY){
            j++;
            targetY = targetY / 2;
        }
        while (targetY - 1 > sourceY){
            j++;
            targetY --;
        }
        return Math.max(i , j);
    }
}
