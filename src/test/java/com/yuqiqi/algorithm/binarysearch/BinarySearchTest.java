package com.yuqiqi.algorithm.binarysearch;

import com.yuqiqi.algorithm.binarysearch.BinarySearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试基础二分
 */
class BinarySearchTest {
    @Test
    void testBinarySearchBasic() {
        int[] a = {7,12,18,48,551,1254};
        assertEquals(0, BinarySearch.binarySearchBasic(a,7));
        assertEquals(1, BinarySearch.binarySearchBasic(a,12));
        assertEquals(2,BinarySearch.binarySearchBasic(a,18));
        assertEquals(3,BinarySearch.binarySearchBasic(a,48));
        assertEquals(4,BinarySearch.binarySearchBasic(a,551));
    }
}