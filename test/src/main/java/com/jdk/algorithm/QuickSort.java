package com.jdk.algorithm;

/**
 * @author purensong
 * @date 2019/4/11 18:07
 * 快排
 * 平均时间复杂度O(N*logN)
 * 空间复杂度O(N)
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
        quickSort(0, arr.length - 1, arr);

    }

    private static void quickSort(int i, int j, int[] arr) {
        int left = new Integer(i);
        int right = new Integer(j);
        int k = arr[i];//标志位

        while (left < right) {
            //自右边向左找到第一个小于标志位的数
            while (left < right && arr[right] > k) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }
            //自左向右找第一个大于标志位的数
            while (left < right && arr[left] < k) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = k;
        System.out.println("left:" + left + ",right:" + right + ",key:" + k);
        for (int ii = 0; ii < arr.length; ii++) {
            System.out.print(arr[ii] + "\t");
        }
        System.out.println();
        if (left - 1 > i) quickSort(i, left - 1, arr);
        if (right + 1 < j) quickSort(right + 1, j, arr);
    }

}
