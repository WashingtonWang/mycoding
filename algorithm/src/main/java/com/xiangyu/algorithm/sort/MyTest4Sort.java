package com.xiangyu.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/16 21:54
 */
public class MyTest4Sort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 34, 100, 3, 45, 11, 56, 35, 20, 10};

        //maoPao20210316(arr);
        //insert20210316(arr);
        //select20210316(arr);
        //arr = guiBing20210317(arr);
        quickSort20210318(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    //冒泡
    public static void maoPao20210316(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        for (int i = 0; i < arr.length; ++i) {
            boolean isDone = false;
            for (int j = 0; j < arr.length - i - 1; ++j) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    isDone = true;
                }
            }
            if (!isDone) {
                break;
            }
        }
    }

    //插入
    private static void insert20210316(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        for (int i = 1; i < arr.length; ++i) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    //选择
    private static void select20210316(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; ++i) {
            //int temp = arr[i];
            int index = i;
            int mix = arr[i];
            for (int j = i + 1; j < arr.length; ++j) {
                if (mix > arr[j]) {
                    index = j;
                    mix = arr[j];
                }
            }
            if (i != index) {
                arr[index] ^= arr[i];
                arr[i] ^= arr[index];
                arr[index] ^= arr[i];
            }
        }
    }

    //归并
    public static int[] guiBing20210317(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int medium = arr.length >> 1;

        int[] arrLeft = Arrays.copyOfRange(arr, 0, medium);
        int[] arrRight = Arrays.copyOfRange(arr, medium, arr.length);

        return mergeSort(guiBing20210317(arrLeft), guiBing20210317(arrRight));
    }

    public static int[] mergeSort(int[] left, int[] right) {
        int[] newArr = new int[left.length + right.length];
        int index = 0, leftIndex = 0, rightIndex = 0;

        for (; index < newArr.length; index++) {
            if (leftIndex >= left.length) {
                newArr[index] = right[rightIndex];
                ++rightIndex;
            } else if (rightIndex >= right.length) {
                newArr[index] = left[leftIndex];
                ++leftIndex;
            } else if (left[leftIndex] < right[rightIndex]) {
                newArr[index] = left[leftIndex];
                ++leftIndex;
            } else if (right[rightIndex] < left[leftIndex]) {
                newArr[index] = right[rightIndex];
                ++rightIndex;
            }
        }

        return newArr;
    }

    //快排
    public static int[] quickSort20210318(int[] arr, int left, int right) {
        if (left >= right) {
            return arr;
        }
        int pivot = partition(arr, left, right);
        quickSort20210318(arr, left, pivot - 1);
        quickSort20210318(arr, pivot + 1, right);

        return arr;
    }

    public static int partition(int[] arr, int left, int right) {
        int i = left;
        int pivot = arr[right];
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                i++;
            }
        }
        if (i != left) {
            int temp = arr[i];
            arr[i] = arr[right];
            arr[right] = temp;
        }
        System.out.println(i);
        return i;
    }

}
