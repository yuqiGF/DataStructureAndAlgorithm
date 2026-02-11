package com.yuqiqi.leetcode.design;

import java.util.*;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * 实现 Twitter 类：
 * Twitter() 初始化简易版推特对象
 * void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId 。
 * List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近  10 条推文的 ID 。新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。推文必须 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 */
public class E355_Twitter {
    class Twitter {
        //抽象出一些对象
        //推文类  设计成链表
        static class Tweet{
            int id;
            int time;
            Tweet next;

            public Tweet(int id, int time, Tweet next) {
                this.id = id;
                this.time = time;
                this.next = next;
            }

            public int getId() {
                return id;
            }

            public int getTime() {
                return time;
            }
        }
        //用户类
        public class User{
            int id;

            public User(int id) {
                this.id = id;
            }

            Set<Integer> followees = new HashSet<>();  //关注着
            Tweet head = new Tweet(-1 , -1 , null);  //推文哨兵
        }

        //用户整体
        private final Map<Integer , User> userMap = new HashMap<>();
        //时间
        private static int time;

        public Twitter() {

        }

        //发送文章
        public void postTweet(int userId, int tweetId) {
            User user = userMap.computeIfAbsent(userId, User::new);//为空的创建  不为空的话返回
            user.head.next = new Tweet(tweetId , time ++ , user.head.next); //新建推文 指向原本第一篇推文指向的推文
        }

        //获取推文
        public List<Integer> getNewsFeed(int userId) {
            //⭐也可以用优先级队列  放入堆顶元素 然后合并多个有序链表
            LinkedList<Tweet> list = new LinkedList<>();  //文章集合
            User user = userMap.computeIfAbsent(userId, User::new);//为空的创建  不为空的话返回
            Tweet curr = user.head;
            while (curr != null){
                if (curr.id != -1){
                    list.add(curr);  //加入
                }
                curr = curr.next;
            }
            for (Integer followee : user.followees) {
                User f = userMap.get(followee);
                Tweet c = f.head;
                while (c != null){
                    if (c.id != -1){
                        list.add(c);  //加入
                    }
                    c = c.next;
                }
            }
            list.sort(new Comparator<Tweet>() {
                @Override
                public int compare(Tweet o1, Tweet o2) {
                    return o2.time - o1.time;
                }
            });
            List<Integer> result = new LinkedList<>();
            if (list.size() >= 10){
                for (int i = 0; i < 10; i++) {
                    result.add(list.get(i).getId());
                }
            }else {
                for (Tweet tweet : list) {
                    result.add(tweet.getId());
                }
            }
            return result;
        }

        //关注
        public void follow(int followerId, int followeeId) {
            User follower = userMap.computeIfAbsent(followerId , User::new);
            User followee = userMap.computeIfAbsent(followeeId , User::new);
            follower.followees.add(followee.id);  //加入关注着列表
        }

        //取关
        public void unfollow(int followerId, int followeeId) {
            if (userMap.containsKey(followerId)){
                userMap.get(followerId).followees.remove(followeeId);
            }
        }
    }
}
