package com.yuqiqi.leetcode.linkedlist;

import com.yuqiqi.leetcode.bean.ListNode;

/**
 * 合并多个升序链表   会两个就一定会多个  两两合并即可  （归并 分治 分 治 合）   ⭐ 二分到两边相等的时候就取出来
 */
public class E23 {
    /**
     * 递归法
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return null;
        }
        if (lists.length == 1){
            return lists[0];
        }
        //调用分割函数将链表分割
        return split(lists, 0 , lists.length - 1);
    }

    /**
     * ⭐分割链表 并二分递归（随便找一个点就行 但是不知道找那个 就找中间吧）   要拆分 总得有边界把  所以另外写一个方法加上边界来递归
     * @param lists  链表数组
     * @param i 左边界
     * @param j 右边界
     * @return 合并后的链表
     */
    public ListNode split(ListNode[] lists, int i , int j){
        //⭐ 结束条件  当链表内只有一个元素的时候返回
        if (i == j){
            return lists[i]; //返回当前唯一链表
        }
        //二分
        int m = (i + j) >>> 1; //右移一位  等价与除以二
        ListNode left = split(lists, i, m);//从i到m  得到左侧的链表
        ListNode right = split(lists, m + 1, j);//从m+1到j  得到右侧的链表
        return margeTwo(left,right); //合并找到的两个单独链表
    }

    /**
     * 合并⭐两个有序链表   多路递归法  参考E21
     * @param list1
     * @param list2
     * @return
     */
    public ListNode margeTwo(ListNode list1 , ListNode list2){
        //结束条件  一个链表为空了就直接返回另一个
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        if (list1.val < list2.val){  //找到较小的 放在前面  后面继续递归找较小的
            list1.next = margeTwo(list1.next,list2);
            return list1;
        }else {
            list2.next = margeTwo(list2.next,list1);
            return list2;
        }
    }

}
