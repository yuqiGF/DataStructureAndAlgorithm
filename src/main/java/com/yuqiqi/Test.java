package com.yuqiqi;

import java.util.Objects;
import java.util.Scanner;

public class Test {
     static class Node{
        public int val;
        public Node next;
        public Node pre;
        public Node(int val , Node next , Node pre){
            this.val = val;
            this.next = next;
            this.pre = pre;
        }
        public Node(int val){
            this.val = val;
        }
    }

    public static Node sentinel = new Node(-1);
    static {
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

     public static void EnQueue(int x){

         Node node = new Node(x);
         node.next = sentinel;
         node.pre = sentinel.pre;
         sentinel.pre.next = node;
         sentinel.pre = node;
     }

     public static void DeQueue(){
         Node node = sentinel.next;
         Node next = node.next;
         sentinel.next = next;
         next.pre = sentinel;
     }

     public static void Rotate(int k){
        //改六个指针
         for (int i = 0; i < k; i++) {
             Node next = sentinel.next;
             Node pre = sentinel.pre;
             Node next1 = next.next;
             sentinel.next = next1;
             sentinel.pre = next;
             next.next = sentinel;
             next.pre = pre;
             pre.next = next;
             next1.pre = sentinel;
         }
     }

     public static int Front(){
        if (sentinel.next == sentinel){
            return -1;
        }else {
            return sentinel.next.val;
        }
     }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = Integer.parseInt(s.nextLine());
        for (int i = 0; i < N; i++) {
            //EDRF
            String[] split = s.nextLine().split(" ");
            if (split.length == 2){
                if (Objects.equals(split[0], "E")){
                    //入队
                    EnQueue(Integer.parseInt(split[1]));
                }else if (Objects.equals(split[0] , "R")){
                    //左旋k个位置
                    Rotate(Integer.parseInt(split[1]));
                }
            }else {
                if (Objects.equals(split[0], "D")){
                    //将前端的元素出队
                    DeQueue();
                }else {
                    //取出最前面的元素
                    int front = Front();
                    System.out.println(front);
                }
            }
        }
    }
}
