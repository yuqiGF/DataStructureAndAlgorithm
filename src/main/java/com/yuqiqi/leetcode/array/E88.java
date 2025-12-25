package com.yuqiqi.leetcode.array;

/**
 * ⭐合并两个有序数组  给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 */
public class E88 {
    /**
     * 分别指针遍历，然后合并到新的数组中    归并到新的数组里   ⭐可以通过从后向前合并 而进心原地合并
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[nums1.length]; //结果数组
        int i = 0;
        int j = 0;
        int r = 0;
        while(i < m && j < n){  //这里不能是i ！= m - 1 这样会导致漏掉最后一个元素
            if (nums1[i] < nums2[j]){
                res[r] = nums1[i];
                i ++;
                r ++;
            }else {
                res[r] = nums2[j];
                j ++;
                r ++;
            }
        }
        //能比的都比完了 可能有剩
        if (j < n){  //1结束了
            System.arraycopy(nums2,j,res,r,n - j);
        }
        if (i < m){
            System.arraycopy(nums1,i,res,r,m - i);
        }
        //最后把res拷贝到nums1
        System.arraycopy(res,0,nums1,0,m+n);
    }

    /**
     * 方法二  从后向前合并  原地合并  节省空间复杂度 （略）
     */

    /**
     * 方法三  递归    参数有第一个数组的起使 结束  第二个数组的起始 结束  结果数组的索引   ⭐递归的关键是起始位置和结束位置
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n){
        int[] res = new int[m+n]; //结果数组
        merge3(nums1,0,m - 1,nums2,0,n - 1,res,0);
        System.arraycopy(res,0,nums1,0,m+n);
    }

    public static void merge3(int[] a1, int i , int iEnd, int[] a2, int j , int jEnd , int[] res , int k){
        //结束条件
        if (i > iEnd){  //a1结束
            System.arraycopy(a2,j,res,k,jEnd - j + 1);
            return;
        }
        if (j > jEnd){
            System.arraycopy(a1,i,res,k,iEnd - i + 1);
            return;
        }
        //比较
        if (a1[i] < a2[j]){
            res[k] = a1[i] ; //找到较小的 然后放到结果数组里
            merge3(a1,i+1, iEnd ,a2, j, jEnd, res, k+1); //递归调用
        }else {
            res[k] = a2[j];
            merge3(a1,i , iEnd,a2,j + 1 , jEnd,res,k+1); //递归调用
        }
    }

    /**
     * ⭐方法三  递归   题目便变种 ：将数组中的两个有序区间（i到iEnd，j到jEnd）内的数组合并
     */
    public static void marge3New(int[] a1 , int i , int iEnd , int j , int jEnd , int[] a2 , int k){
        //某个区间没有元素的时候结束递归
        if (i > iEnd){  //i没元素l
            System.arraycopy(a1,j,a2,k,jEnd - j + 1); //复制剩余元素
            return;
        }
        if (j > jEnd){
            System.arraycopy(a1,i,a2,k,iEnd - i + 1);
            return;
        }
        //比较
        if (a1[i] < a1[j]){
            //把较小的放到新的结果数组中
            a2[k] = a1[i];
            marge3New(a1,i+1,iEnd,j,jEnd,a2,k+1);  //递归
        }else {
            a2[k] = a2[j];
            marge3New(a1,i,iEnd,j + 1,jEnd,a2,k + 1);
        }
    }
}
