package com.xiangyu.algorithm.ksinterview;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/21 13:36
 */
public class GuiBingSortArithTest {

    //public static void main(String[] args) {
    //    int[] arr = {2, 3, 1, 0, 5, 45, 90, 67, 23, 12, 7, 88, 44, 55, 22};
    //    arr = guibingSortMain(arr);
    //
    //    sout(arr);
    //}

    public static void sout(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 5, 45, 90, 67, 23, 12, 7, 88, 44, 55, 22};
        MergeSort2(arr);
    }


    public static void MergeSort2(int[] arr) {
        //使用非递归的方式来实现归并排序
        int len = arr.length;
        int k = 1;

        while (k < len) {
            MergePass(arr, k, len);
            k *= 2;
        }

        sout(arr);
    }

    //MergePass方法负责将数组中的相邻的有k个元素的字序列进行归并
    private static void MergePass(int[] arr, int k, int n) {
        int i = 0;

        //从前往后,将2个长度为k的子序列合并为1个
        while (i < n - 2 * k + 1) {
            merge(arr, i, i + k - 1, i + 2 * k - 1);
            i += 2 * k;
        }

        //这段代码保证了，将那些“落单的”长度不足两两merge的部分和前面merge起来。
        if (i < n - k) {
            merge(arr, i, i + k - 1, n - 1);
        }

    }

    //merge函数实际上是将两个有序数组合并成一个有序数组
    //因为数组有序，合并很简单，只要维护几个指针就可以了
    private static void merge(int[] arr, int low, int mid, int high) {
        //temp数组用于暂存合并的结果
        int[] temp = new int[high - low + 1];
        //左半边的指针
        int i = low;
        //右半边的指针
        int j = mid + 1;
        //合并后数组的指针
        int k = 0;

        //将记录由小到大地放进temp数组
        for (; i <= mid && j <= high; k++) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i++];
            } else {
                temp[k] = arr[j++];
            }
        }

        //接下来两个while循环是为了将剩余的（比另一边多出来的个数）放到temp数组中
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= high) {
            temp[k++] = arr[j++];
        }

        //将temp数组中的元素写入到待排数组中
        for (int l = 0; l < temp.length; l++) {
            arr[low + l] = temp[l];
        }
    }


    /**
     * 以下函数 以后再做研究
     */
    //public static void main(String[] args) {
    //    int[] nums = {6, 5, 3, 8, 1, 7, 2, 9, 4};
    //    for (int i = 1; i <= nums.length; i *= 2) {
    //        for (int j = 0; j + i <= nums.length; j += i * 2) {
    //            //Math.min 的目的是处理 整个待排序数组为奇数的情况
    //            merge(nums, j, j + i - 1, Math.min(j + 2 * i - 1, nums.length - 1));
    //        }
    //    }
    //    System.out.println(Arrays.toString(nums));
    //}

    ///**
    // * 两两归并排好序的数组（2路归并）
    // *
    // * @param nums   带排序数组对象
    // * @param left   左边数组的第一个索引
    // * @param center 左数组的最后一个索引，center + 1右数组的第一个索引
    // * @param right  右数组的最后一个索引
    // */
    //private static void merge(int[] nums, int left, int center, int right) {
    //    int[] tmpArray = new int[right - left + 1];
    //    int leftIndex = left;   //左数组第一个元素的索引
    //    int rightIndex = center + 1;   //右数组第一个元素索引
    //    int tmpIndex = 0;    //临时数组索引
    //
    //    // 把较小的数先移到新数组中
    //    while (leftIndex <= center && rightIndex <= right) {
    //        if (nums[leftIndex] <= nums[rightIndex]) {
    //            tmpArray[tmpIndex++] = nums[leftIndex++];
    //        } else {
    //            tmpArray[tmpIndex++] = nums[rightIndex++];
    //        }
    //    }
    //
    //    // 把左边剩余的数移入数组
    //    while (leftIndex <= center) {
    //        tmpArray[tmpIndex++] = nums[leftIndex++];
    //    }
    //
    //    // 把右边边剩余的数移入数组
    //    while (rightIndex <= right) {
    //        tmpArray[tmpIndex++] = nums[rightIndex++];
    //    }
    //
    //    // 把新数组中的数覆盖nums数组
    ///*for (int i = 0; i < tmpArray.length; i++) {
    //    nums[begin + i] = tmpArray[i];
    //}*/
    //    //可以优化成下面的写法
    //    System.arraycopy(tmpArray, 0, nums, left, tmpArray.length);
    //}
}
