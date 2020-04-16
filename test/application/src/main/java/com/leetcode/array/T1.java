package com.leetcode.array;

/**
 * @author rensong.pu
 * @date 2020/3/17 16:16 星期二
 **/
public class T1 {

    /**
     * 旋转数组 输入[4,5,6,0,1,2]
     */
    static class Solution {
        public int minArray(int[] numbers) {
            if (numbers.length == 0) {
                return 0;
            }
            return minArr(numbers, 0, numbers.length - 1);
        }

        public int minArr(int[] n, int l, int r) {
            if (l == r) {
                return n[l];
            }
            int m = (l + r) >> 1;
            if (n[m] > n[r]) {
                return minArr(n, m + 1, r);
            } else if(n[m]==n[r]) {
                return minArr(n,l,r-1);
            }else {
                return minArr(n, l, m);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{4, 5, 1, 2, 3};
        int[] arr3 = new int[]{2, 2, 2, 0, 1};
        int[] arr4 = new int[]{2,2,2,2, 0, 1, 2};
        System.out.println(new Solution().minArray(arr));
        System.out.println(new Solution().minArray(arr2));
        System.out.println(new Solution().minArray(arr3));
        System.out.println(new Solution().minArray(arr4));
    }
}
