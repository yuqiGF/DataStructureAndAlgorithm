package com.yuqiqi.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 分数背包问题   贪心⭐   先拿最贵的 性价比最高的
 */
public class FractionalKnapsack {
    static class Item{
        int index;
        int weight;  //物品剩余重量
        int value;  //物品总价格

        public Item(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "index=" + index +
                    ", weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(0,4,24),
                new Item(1,8,160),
                new Item(2,2,4000),
                new Item(3,6,108),
                new Item(4,1,4000),
        };
        System.out.println(select(items, 10)); //假设最大能装10kg
    }

    /**
     * 贪心   每次选择性价比最高的那个
     * @param items 物品数组
     * @param total 最大能拿的重量
     */
    static int select(Item[] items, int total){
        //先按性价比排个序
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o2.value / o2.weight - o1.value / o1.weight;
            }
        });

        int value = 0;
        for (Item item : items) {
            if (total >= item.weight){  //能拿完
                total -= item.weight;
                value += item.value;
            }else {  //拿不完
                value += item.value / item.weight * total;
                return value;
            }
        }
        return value;
    }

}
