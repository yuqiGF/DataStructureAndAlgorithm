package com.yuqiqi.leetcode.design;

import java.util.Random;

/**
 * 不使用任何库函数，设计一个 跳表 。
 * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * 了解更多 : https://oi-wiki.org/ds/skiplist/
 * 在本题中，你的设计应该要包含这些函数：
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 */
public class E1206_SkipList {
    /*
        ⭐next指针指向null 向下    next指针指向的节点值 >= 目标值  向下
          next指向不为null 且指向的节点值 < 目标值  向右
     */
    class Skiplist {
        private static final int MAX = 10;  //最大指针容量  ⭐redis是32   java是62
        private final Node head = new Node(-1);  //头节点

        //节点类
        static class Node{
            int val;
            Node[] next = new Node[MAX];  //指向其他节点的指针   这里写死了  好写一些

            public Node(int val) {
                this.val = val;
            }
        }

        //通用查找 （下楼梯方式）  返回走过的路径
        public Node[] find(int val){
            Node[] path = new Node[MAX];  //⭐记录路径
            Node curr = head;
            for(int level = MAX - 1 ; level >= 0 ; level --){  //从上向下
                //⭐一直向右   （下一个值大于val才跳出 此时的值仍然小于val）
                while (curr.next[level] != null && curr.next[level].val < val){
                    curr = curr.next[level];
                }
                path[level] = curr;  //记录路径
            }
            return path;
        }

        public Skiplist() {

        }

        //是否在跳表中
        public boolean search(int target) {
            Node[] nodes = find(target);
            Node node = nodes[0].next[0];  //路径第0层的next节点
            return node != null && node.val == target;
        }

        //添加节点
        public void add(int num) {
            //1、确定添加位置
            Node[] path = find(num);
            //2、创建随机高度的节点
            Node node = new Node(num);
            int level = randomLevel(MAX);  //随机高度   从1开始逐渐减半
            //3、修改路径节点的next指针和新节点的next指针
            for (int i = 0; i < level; i++) {
                node.next[i] = path[i].next[i];
                path[i].next[i] = node;
            }
        }

        //删除节点
        public boolean erase(int num) {
            //直接把被删除节点绕过去即可
            Node[] path = find(num);
            Node node = path[0].next[0];
            if (node == null || node.val != num){  //⭐判断有没有  是不是要删除的
                return false;  //没有  删除失败
            }
            for (int i = 0; i < MAX; i++) {
                if (path[i].next[i] != node){  //注意 ⭐这里用node判断  如果用值判断的话 遇到重复值的话就炸了
                    break;  //⭐高度超了 就跳出
                }
                path[i].next[i] = node.next[i];  //同层指针改为被删的next
            }
            return true;
        }


        //设计从1开始到max，概率逐渐减半得返回数字的方法  ⭐随机生成节点的高度
        static Random r = new Random();
        public static int randomLevel(int max){
            //                  50                      25                      12.5
//        return r.nextBoolean() ? 1 : r.nextBoolean() ? 2 : r.nextBoolean() ? 3 : 4;
            int x = 1;
            while (x < max){
                if (r.nextBoolean()){  //概率砍半
                    return x;
                }
                x ++;
            }
            return x;
        }

    }
}
