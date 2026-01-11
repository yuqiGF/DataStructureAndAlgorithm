package com.yuqiqi.dataStructure.tree.bTree;

import java.util.Arrays;

/**
 * B树  一种更适合于存储的数据结构   可以有效缩短树高  减少磁盘io
 * 度：degree：树中节点孩子数
 * 阶：order：所有孩子节点数的最大值
 * 孩子数：ceil（m / 2） ~ m
 * 关键字数：ceil（m / 2） - 1 ~ m - 1
 */
public class BTree {
    static class Node{
        int[] keys; //关键字  不止一个
        Node[] children; //节点  不止一个
        int keyNumber; //有效关键字数
        boolean leaf = true;  //是否是叶子节点
        int t;  //树的最小度数  最小孩子数

        public Node(int t) {  //t >= 2
            this.t = t;
            this.children = new Node[2 * t];  //最小度数 * 2 就是最大孩子数
            keys = new int[2 * t - 1];
        }

        //辅助方法  打印有效关键字
        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys,0,keyNumber));
        }

        //辅助方法 多路查找
        Node get(int key){
            int i = 0; //key指针  用来确定孩子的位置
            while(i < keyNumber){
                if (keys[i] == key){
                    return this;  //找到了
                }
                if (keys[i] > key){
                    break;  //找到比他大的了  退出 到下一个节点中去找
                }
                i ++;  //比节点中的key小的话继续找
            }
            //此时keys[i] > key 或者 i == keyNumber
            if (leaf){
                return null; //如果查找的是叶子节点 直接就没了
            }
            //非叶子节点情况
            return children[i].get(key);  //直接到第i个孩子中去查找
        }

        //向指定索引插入key
        void insertKey(int key , int index){
            System.arraycopy(keys,index,keys,index + 1,keyNumber - index);  //向后拷贝一位
            keys[index] = key; //插入key
            keyNumber ++;
        }

        //向指定索引插入child
        void insertChild(Node child , int index){
            System.arraycopy(children,index,children,index + 1,children.length - index);  //向后拷贝一位
            children[index] = child;
        }
    }

    Node root;  //根节点
    int t;  //树中节点的最小度数
    final int MAX_KEY_NUMBER;  //最大key
    final int MIN_KEY_NUMBER;  //最小key

    public BTree() {
        this(2);  //无参时默认调用有参 指定默认值为2 不能再小了
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1; //最下key就是孩子数 - 1
    }

    /**
     * 判断一个key是否存在
     */
    public boolean contains(int key){
        return root.get(key) != null;
    }

    /**
     * 新增一个key   查找本节点中的插入位置   如果没有空位的话 更新
     * 有空位的话  如果是叶子节点  直接插入   如果是非叶子节点  继续再children[i]处递归插入
     * ⭐记得限制拆入的最大值  达到最大值后要进行分列split
     */
    public void put(int key){
        doPut(root,key);
    }

    //递归插入
    private void doPut(Node node , int key){

        //
    }

    /**
     * 删除一个key
     */



}
