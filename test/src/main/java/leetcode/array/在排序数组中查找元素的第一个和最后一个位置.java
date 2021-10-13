package leetcode.array;
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1164 👎 0


/**
 * @author rensong.pu
 * @date 2021/8/25 14:23 星期三
 **/
public class 在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] newnums = new 在排序数组中查找元素的第一个和最后一个位置().searchRange(nums, target);
        for (int i = 0; i < newnums.length; i++) {
            System.out.print(newnums[i] + "\t");
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[]{-1, -1};
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                int i = mid;
                while (i > 1 && nums[i] == nums[--i]) ;
                result[0] = i + 1;
                int j = mid;
                while (j < nums.length - 1 && nums[j] == nums[++j]) ;
                result[1] = j - 1;
                break;
            }
        }
        return result;
    }
}
