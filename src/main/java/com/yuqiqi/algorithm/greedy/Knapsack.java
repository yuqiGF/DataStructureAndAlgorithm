package com.yuqiqi.algorithm.greedy;


import java.util.Arrays;

/**
 * ⭐01背包问题  贪心算法  这次物品无法分割 ❌ 贪心算法无法达到最优解！！
 * n个物品都是固体，有重量和价值
 * 要取走不超过10克的物品
 * 每次可以不拿或者全拿
 */
public class Knapsack {
    static class Item {
        int index;
        int weight;  //物品重量
        int value;  //物品价格

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
                new Item(0, 4, 24),
                new Item(1, 8, 160),
                new Item(2, 2, 4000),
                new Item(3, 6, 108),
                new Item(4, 1, 4000),
        };
        System.out.println(select(items, 10)); //假设最大能装10kg
    }

    public static int select(Item[] items, int total) {
        Arrays.sort(items, (a, b) -> Integer.compare(b.value / b.weight, a.value / a.weight)); //按性价比排个序
        int value = 0;  //总价值
        for (Item item : items) {
            if (total >= item.weight){  //拿的了
                value += item.value;
                total -= item.weight;
            }
        }
        return value;
    }
}
