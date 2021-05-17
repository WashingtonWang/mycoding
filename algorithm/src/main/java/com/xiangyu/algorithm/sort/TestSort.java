package com.xiangyu.algorithm.sort;

import java.util.Arrays;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/4/22 16:04
 */
public class TestSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 34, 100, 3, 45, 11, 56, 35, 20, 10};

        //swap(arr, 0, 1);
        //maopaoSort(arr);
        //xuanzeSort(arr);
        //charuSort(arr);
        //xierSort(arr);
        //arr = guibingSort(arr);
        //kuaiSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void kuaiSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start;
        int j = end;

        int baseVal = arr[start];
        while (i < j) {

            while (i < j && arr[j] >= baseVal) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[i] < baseVal) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = baseVal;

        kuaiSort(arr, start, i - 1);
        kuaiSort(arr, i + 1, end);
    }

    public static int[] guibingSort(int[] arr) {
        assert arr != null;
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(guibingSort(left), guibingSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    public static void xierSort(int[] arr) {
        assert arr != null;
        int gap = arr.length / 2, temp;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    public static void charuSort(int[] arr) {
        assert arr != null;
        int current;
        for (int i = 0; i < arr.length - 1; i++) {
            current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    public static void xuanzeSort(int[] arr) {
        assert arr != null;
        for (int i = 0; i < arr.length; i++) {
            int location = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    location = j;
                }
            }
            if (location != i) {
                swap(arr, location, i);
            }
        }
    }

    public static void maopaoSort(int[] arr) {
        assert arr != null;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void swap(int[] arr, int l, int r) {
        arr[l] = arr[l] ^ arr[r];
        arr[r] = arr[r] ^ arr[l];
        arr[l] = arr[l] ^ arr[r];
    }
}
