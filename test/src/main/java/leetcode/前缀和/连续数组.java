package leetcode.前缀和;
//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
//
//
// 示例 1:
//
//
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
//
// 示例 2:
//
//
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// nums[i] 不是 0 就是 1
//
// Related Topics 数组 哈希表 前缀和 👍 464 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2021/9/28 16:04 星期二
 **/
public class 连续数组 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,0};
        System.out.println(new 连续数组().findMaxLength(nums));
    }

    /**
     * 前缀和思想，sum[i] 为前i个数和（i从1开始）
     * 可以将0 转为-1，问题转化为求 最长的一段 和为0 的子数组
     * hash表 存储下标 ，key为sum，value  为下标
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int[] sum = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 0 ? -1 : nums[i - 1]);
        }
        int len = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 由于长度最小为2 ，遍历从2开始
        for (int i = 2; i < sum.length; i++) {
            if (!map.containsKey(sum[i - 2])) {
                map.put(sum[i - 2], i - 2);
            }

            if (map.containsKey(sum[i])) {
                len = Math.max(len, i - map.get(sum[i]));
            }

        }
        return len;
    }
}

