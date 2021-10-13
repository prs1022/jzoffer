package leetcode.前缀和;
//给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1], k = 2
//输出：2
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3], k = 3
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 10⁴
// -1000 <= nums[i] <= 1000
// -10⁷ <= k <= 10⁷
//
// Related Topics 数组 哈希表 前缀和 👍 1112 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2021/9/28 16:40 星期二
 **/
public class 和为k的子数组 {
    public static void main(String[] args) {
        int[] nums = new int[]{0};
        int k = 0;
        int result = new 和为k的子数组().subarraySum2(nums, k);
        System.out.println(result);
    }

    /**
     *
     * 还是前缀和思想 pre_sum[i] 表示前i个数的和 ，i从1 开始
     * pre_sum - k 的 区间个数 即为 要求的 子数组个数
     * 计算完包括了当前数前缀和以后，我们去查一查在当前数之前，有多少个前缀和等于 preSum - k 呢？
     * 这是因为满足 preSum - (preSum - k) == k 的区间的个数是我们所关心的。
     *
     * 对于一开始的情况，下标 0 之前没有元素，可以认为前缀和为 0，个数为 1 个，因此 preSumFreq.put(0, 1);，这一点是必要且合理的。
     * 具体的例子是：nums = [3,...], k = 3，和 nums = [1, 1, 1,...], k = 3。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int[] sum = new int[nums.length + 1];
        //todo 错误实例
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        int count = 0;
        for (int i = 1; i < sum.length; i++) {
            if (map.containsKey(sum[i] - k)) {
                count += map.get(sum[i] - k);
            }
        }
        return count;
    }

    /**
     * 正确解法
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int pre_sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pre_sum += nums[i];
            if (map.containsKey(pre_sum - k)) {
                count += map.get(pre_sum - k);
            }
            map.put(pre_sum,map.getOrDefault(pre_sum,0)+1);
        }
        return count;
    }
}
