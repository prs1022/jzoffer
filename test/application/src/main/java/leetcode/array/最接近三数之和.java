package leetcode.array;

import java.util.Arrays;

/**
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * @author rensong.pu
 * @date 2021/8/12 14:28 星期四
 **/
public class 最接近三数之和 {
    public static void main(String[] args) {
        int[] nums = new int[]{-3, -2, -5, 3, -4};
        int target = -1;
        int result = new 最接近三数之和().threeSumClosest(nums, target);
        System.out.println("最接近三数之和:" + result);

    }


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int value = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                int diff = Math.abs(sum - target);
                int diff2 = Math.abs(value - target);
                if (diff < diff2) {
                    value = sum;
                }

                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return value;
    }

}
