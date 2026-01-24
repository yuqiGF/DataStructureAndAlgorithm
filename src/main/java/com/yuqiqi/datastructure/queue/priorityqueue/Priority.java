package com.yuqiqi.datastructure.queue.priorityqueue;

/**
 * ⭐优先级队列   和队列一样从一端入队  但是出队的时候按照优先级出队 （遍历一遍  找到最大值  然后出队  其他的进行响应移动）
 */
public interface Priority {
    /**
     * 返回优先级
     * @return 约定数字大的优先级高
     */
    int priority();

}
