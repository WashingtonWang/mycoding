package arithmetic.sort;

import java.util.Arrays;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/21 13:36
 */
public class MyTestSortArith {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 5, 45, 90, 67, 23, 12, 7, 88, 44, 55, 22};
        //maoPaoSort(arr);
        //xuanZeSort(arr);
        //chaRuSort2(arr);
        //xierSort(arr);
        arr = guibingSortMain(arr);
        //kuaisuSort(arr, 0, arr.length - 1);

        sout(arr);
    }

    public static void kuaisuSort(int[] arr, int start, int end){
        if (start >= end) {
            return;
        }
        int baseVal = arr[start];
        int i = start;
        int j = end;
        while (i < j){
            while (i < j && arr[j] >= baseVal){
                j--;
            }
            if (i < j){
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < baseVal){
                i++;
            }
            if (i < j){
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = baseVal;
        kuaisuSort(arr, start, i - 1);
        kuaisuSort(arr,  i + 1, end);
    }

    /**
     * 归并排序
     */
    public static int[] guibingSortMain(int[] arr){
        if (arr.length < 2){
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        return guibingSort(guibingSortMain(left), guibingSortMain(right));
    }
    public static int[] guibingSort(int[] left, int[] right){
        int[] newArr = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < newArr.length; index++){
            if (i >= left.length){
                newArr[index] = right[j++];
            } else if (j >= right.length){
                newArr[index] = left[i++];
            } else if(left[i] > right[j]){
                newArr[index] = right[j++];
            } else {
                newArr[index] = left[i++];
            }
        }
        return newArr;
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void xierSort(int[] arr){
        int gap = arr.length / 2;
        while (gap > 0){
            for (int i = gap; i < arr.length; i++){
                int temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp){
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    /**
     * 插入排序 第二种 实现方式
     */
    public static void chaRuSort2(int[] arr){
        int current;
        for (int i = 0; i < arr.length - 1; i++){
            current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]){
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }
    /**
     * 插入排序 第一种 实现方式
     */
    public static void chaRuSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j;
            if (arr[i] < arr[i - 1]){
                int temp = arr[i];
                for (j = i - 1; j >= 0 && temp < arr[j]; j--){
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }

    public static void xuanZeSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            if (i != index) {
                swap(arr, index, i);
            }
        }
    }

    public static void maoPaoSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }


    public static void swap(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        //System.out.println("swap before: left = " + left + " | right = " + right);
        arr[left] ^= arr[right];
        arr[right] ^= arr[left];
        arr[left] ^= arr[right];
        //System.out.println("swap after:  a = " + a + " | b = " + b);
    }

    public static void sout(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
