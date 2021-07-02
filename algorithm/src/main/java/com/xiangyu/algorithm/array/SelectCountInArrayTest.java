package com.xiangyu.algorithm.array;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/25 16:47
 * 写一个函数，输入一个有序数组，用最快的方法找到某个数字出现的次数。如果找不到，返回0
 * 二分查找
 */
public class SelectCountInArrayTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 5};
        test(arr, 1);
    }

    public static void test(int[] array, int b) {
        int left = 0;
        int right = array.length - 1;
        int index = 0;
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            if (array[pivot] == b) {
                index = pivot;
                break;
            } else if (array[pivot] < b) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }

        //System.out.println(index);

        int preIndex = index;
        int endIndex = index;

        while (preIndex >= 0 && array[preIndex] == b) {
            preIndex--;
        }
        while (endIndex <= array.length - 1 && array[endIndex] == b) {
            endIndex++;
        }
        System.out.println(endIndex - preIndex - 1);
    }


}
