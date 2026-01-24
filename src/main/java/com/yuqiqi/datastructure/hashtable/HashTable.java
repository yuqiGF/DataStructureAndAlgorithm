package com.yuqiqi.datastructure.hashtable;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * 哈希表     数组+链表
 * 给每份数据分配一个编号  放入数组  建立编号于数组索引的关系
 * ⭐有限长度的数组  拉链式存储    ⭐允许编号适当重复，通过数据自身来区分
 */
public class HashTable {
    //节点类  用来组成链表
    static class Entry{
        int hash;  //哈希码  用来确定索引位置
        Object key;  //键
        Object value;  //值
        Entry next;  //next指针

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    //数组    由每个链表的头节点组成的数组
    Entry[] table = new Entry[16];  //⭐这里的初始值设置为2的n次方  方便按位与运算来把求模替换成按位与  性能更高
    int size = 0; //元素个数
    float loadFactor = 0.75f;  //负载因子    ⭐ 当元素个数超过数组长度*负载因子时候就应该扩容   容量翻倍
    int threshold = (int) (loadFactor * table.length);  //阈值

    /* ⭐最简单的求数据存在数组中的位置的方法：挨个循环 -> 求模运算
     * ⭐求模运算替换为按位与运算
     * - 前提：数组长度是2的n次方
     * - hash % 数组长度 <==> hash & （数组长度 - 1）     余数就是原本数值的后 长度 - 1 位
     */

    /**
     * 根据哈希码获取value
     */
    Object get(int hash, Object key){
        int idx = hash & table.length - 1;  //根据哈希码找到该存的索引位置
        if (table[idx] == null){
            return null;  //空的  没有
        }
        //不为null的话就是一个链表的⭐头节点
        Entry p = table[idx];  //正常的链表遍历操作
        while(p != null){
            if (p.key.equals(key)){  //对象用equals比较
                return p.value;
            }
            p = p.next;
        }
        return null;  //没有的话就是null了
    }

    /**
     * 向hash表存入新的key value 如果key重复 则更新value
     */
    void put(int hash , Object key , Object value){
        int idx = hash & (table.length - 1);  //根据哈希码找到该存的索引位置
        if (table[idx] == null){  //该位置为空
            table[idx] = new Entry(hash,key,value); //直接新增
        }else {
            //不为空  正常遍历
            Entry p = table[idx];
            while (true){
                if (p.key.equals(key)){  //key重复
                    p.value = value; //更新value
                    return;
                }
                if (p.next == null){
                    break;  //找到最后一个节点p
                }
                p = p.next;
            }
            //不存在
            p.next = new Entry(hash, key, value);
        }
        size ++;  //新增完后个数加一
        //扩容
        if (size > threshold){
            resize();
        }
    }

    /**
     * 扩容方法
     */
    private void resize() {
        //新数组  容量为原来的数组的2倍   ⭐左移一位  相当于乘法 性能更好
        Entry[] newTable = new Entry[table.length << 1];
        //逐一移动旧数组中的链表   （因为由哈希值计算的索引位置会发生变化  所以不能直接拷贝）
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];  //每个链表的头
            if (p != null) {
                //拆分数组  并且移动到新数组中
                /*
                    ⭐拆分规律：一个链表最多拆成两个   将哈希值和数组长度与运算
                    hash & table.length == 0 的一组  hash & table.length != 0 的一组
                 */
                Entry a = null;  //两个新的空链表的头指针
                Entry b = null;
                Entry aHead = null;
                Entry bHead = null;
                while(p != null){
                    if ((p.hash & table.length) == 0){
                        //分配到 a 链表
                        if (a != null){
                            a.next = p;  //接到a指针的后面   连上
                        }else {
                            aHead = p;
                        }
                        a = p;  //更新a为最新的p
                    }else {
                        //分配到 b 链表
                        if (b != null){
                            b.next = p;
                        }else {
                            bHead = p;
                        }
                        b = p;
                    }
                    p = p.next;
                }
                //让链表的末尾指向null
                if (a != null){
                    a.next = null;
                    newTable[i] = aHead;  //更新链表头
                }
                if (b != null){
                    b.next = null;
                    newTable[i] = bHead;
                }
            }
        }

        table = newTable;  //更新table
        threshold = (int) (loadFactor * table.length);  //更新阈值
    }

    /**
     * 根据哈希码删除值
     */
    Object remove(int hash , Object key){
        int idx = hash & (table.length - 1);  //相当于取模运算 计算索                      引位置
        if (table[idx] == null){
            return null;  //没东西删
        }
        Entry p = table[idx]; //头节点
        Entry prev = null;
        while(p != null){
            if (p.key == key){
                //删除
                if (prev == null){
                    table[idx] = p.next;  //删的是头  直接接下一个节点即可
                }else {
                    prev.next = p.next;
                }
                size --;
                p.next = null;  //help GC
                return p.value;
            }
            prev = p; //记录上一个节点
            p = p.next;
        }
        return null;
    }

    /**
     * 哈希算法   将任意一个对象分配一个编号的过程    （编号是一个又一定范围的数字） 如int
     * 常见的哈希算法有：MD5  SHA256  SHA512  CRC32等    但是不可避免得会有冲突
     * 也可以叫 “摘要算法   散列算法”    java中可以直接调用Object类中的hashcode方法生成哈希值
     */
    private static int getHash(Object key) {
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);   //⭐和高16位做一个异或运算  减少冲突   （除以同一个数余数可能一样 让前面的位数也用上就可以减少冲突）
    }

    public Object get(Object key){
        int hash = getHash(key);
        return get(hash,key);
    }

    public void put(Object key , Object value){
        int hash = getHash(key);
        put(hash,key,value);
    }

    public Object remove(Object key){
        int hash = getHash(key);
        return remove(hash,key);
    }

    /**
     * 问题：字符串的哈希码 不能简单的调用Object类中的方法  那样会造成字符串一样，但是哈希码不一样的情况
     * jdk中的解决方法：让字符串中的每个字符转化成int类型，然后每一位乘以不同的权重（质数效果更好  一般是31）⭐
     */
    public static void main(String[] args) {
        String a = "abc";
        System.out.println(a.hashCode());
        //String的hashcode方法实现
        int hash = 0;
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
//            hash = hash * 31 + c;   //jdk中就是这么计算字符串的hash的
            //优化
//            hash = hash * 32 - hash + c;
            hash = (hash << 5) - hash + c;    //⭐*5可以优化为左移五位  效率更高
        }
        System.out.println(hash);

        /**
         * MurmurHash  非加密哈希算法  ⭐速度更快 碰撞率低（1亿数据最多有两个碰撞）  随机性强
         * 使用需要导入google的guava包
         */
        System.out.println(Hashing.murmur3_32().hashString("abc", StandardCharsets.UTF_8).asInt());
    }
/* ⭐
为什么采用尾插：因为头插在高并发环境下容易死锁
为什么jdk采用高低位异或的操作 ： 某些情况下低位可能完全一样  这样求余数得到的结果就容易冲突了
能否不用2的n次方作为数组容量：  可以  也能用质数   质数的分散性好一点    但是求余数效率不高（不能用位运算来优化）
jdk中的hashMap在链表长度过长（超过8 且数组长度超过64时）的时候会转化为红黑树是为什么：  ⭐主要是防止有心人攻击，一般20万的数据都只会产生长度为6的链表  其他都通过数组扩容分散开了    一般长度都不会超过6
 */





}
