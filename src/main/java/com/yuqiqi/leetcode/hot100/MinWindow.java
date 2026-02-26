package com.yuqiqi.leetcode.hot100;

/**
 * 最小覆盖字串  ⭐滑动窗口   满足条件后再去缩短左指针
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        //需要满足的模板
        int[] template = new int[128];
        for (char c : tc) {
            template[c] ++;
        }

        //结果
        String result = "";

        //⭐重点是记录条件总数  （需要满足几种字母）
        int requires = 0;
        for (int q : template) {
            if (q > 0){
                requires ++;
            }
        }

        //达到需求的总数
        int count = 0;
        int i = 0;
        int j = 0;
        //还有一个重点就是要记录原始字符串中各个字符的出现次数  好做对比
        int[] origin = new int[128];
        while (i < sc.length){
            int right = sc[i];
            origin[right] ++;
            //判断是否已满足
            if (origin[right] == template[right]){
                count ++;
            }
            //全部满足   另一个指针开始移动
            while (count == requires && j <= i){
                if (result.isEmpty() || result.length() > i - j + 1){
                    //找更短的 更新结果
                    result = s.substring(j , i + 1);
                }
                //更新原始记录表
                char left = sc[j];
                origin[left] --;
                if (origin[left] < template[left]){  //⭐这里不能用不等于
                    count --;  //不满足了
                }
                j ++;
            }
            i ++;
        }
        return result;
    }
}
