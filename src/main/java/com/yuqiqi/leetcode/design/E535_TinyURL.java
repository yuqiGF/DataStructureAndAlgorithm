package com.yuqiqi.leetcode.design;

import java.util.HashMap;

/**
 * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk  ⭐这是一个62进制的数字 。请你设计一个类来加密与解密 TinyURL 。
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 * 实现 Solution 类：
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 */
public class E535_TinyURL {
    //⭐当然 原封不动的返回回去也可以？  哈哈哈

    //关键  长短网站对应起来  用两个map对应起来
    public class Codec {
        private HashMap<String , String> longToShort = new HashMap<>();
        private HashMap<String , String> shortToLong = new HashMap<>();
        /*
            ⭐方案一  随机数
            ⭐方案二  哈希值
            ⭐方案散  递增数
         */
        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            if (longToShort.containsKey(longUrl)){
                return longToShort.get(longUrl);
            }
            int code = longUrl.hashCode();
            String shortUrl = "http://tinyurl.com/" + code;
            longToShort.put(longUrl , shortUrl);
            shortToLong.put(shortUrl , longUrl);
            return shortUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return shortToLong.get(shortUrl);
        }
    }
}
