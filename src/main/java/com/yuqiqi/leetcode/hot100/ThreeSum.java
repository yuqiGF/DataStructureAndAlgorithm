package com.yuqiqi.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        //回溯
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        rec(3 , 0 , nums.length - 1 , nums , 0 , new LinkedList<>() , result);
        return result;
    }

    //逐渐化简
    public void rec(int n , int i , int j , int [] nums , int target ,
                    LinkedList<Integer> stack , List<List<Integer>> result){
        if (n == 2){
            twoSum(i ,j , nums , target , stack , result);
            return;
        }
        for (int k = i; k < j; k++) {
            if (k > i && nums[k] == nums[k - 1]){
                continue;
            }//去重
            stack.push(nums[k]);
            rec(n - 1 , k + 1 , j , nums , target - nums[k] , stack , result);
            stack.pop();
        }
    }

    public void twoSum(int i , int j , int[] nums, int target ,
                       LinkedList<Integer> stack , List<List<Integer>> result){
        //排好序的两数之和
        while (i < j){
            int sum = nums[i] + nums[j];
            if (sum == target){
                //找到了  此时stack里面有前几个数
                ArrayList<Integer> list = new ArrayList<>(stack);
                list.add(nums[i]);
                list.add(nums[j]);
                result.add(list);
                i ++;
                j --;
                while (i < j && nums[i] == nums[i - 1]){
                    i ++;
                }
                while (i < j && nums[j] == nums[j + 1]){
                    j --;
                }
            }else if (sum < target){
                i ++;
            }else{
                j --;
            }
        }
    }
}
