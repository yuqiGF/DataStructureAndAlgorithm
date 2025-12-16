package com.yuqiqi.algorithm.binarysearch;

import com.yuqiqi.dataStructure.linkedList.SinglyLinkedList;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {

    /**
     * 头插
     */
    @Test
    void addFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.loop1(value -> {
            System.out.println(value);
        });

        list.loop2(System.out::println);

        //测试迭代器  增高for循环本质就是迭代器
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    @Test
    void addLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.loop1(System.out::println);
        System.out.println(list.get(3));
        list.get(19);
    }

    @Test
    void fool(){
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}