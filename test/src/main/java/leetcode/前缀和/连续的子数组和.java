package leetcode.前缀和;
//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
//
// 子数组大小 至少为 2 ，且
// 子数组元素总和为 k 的倍数。
//
//
// 如果存在，返回 true ；否则，返回 false 。
//
// 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
//
//
//
// 示例 1：
//
//
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
//
// 示例 2：
//
//
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//
//
// 示例 3：
//
//
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// 0 <= nums[i] <= 10⁹
// 0 <= sum(nums[i]) <= 2³¹ - 1
// 1 <= k <= 2³¹ - 1
//
// Related Topics 数组 哈希表 数学 前缀和 👍 369 👎 0


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rensong.pu
 * @date 2021/9/27 15:26 星期一
 **/
public class 连续的子数组和 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0};
        int k = 1;
        final boolean b = new 连续的子数组和().checkSubarraySum(nums, k);
        System.out.println("存在：" + b);
    }

    /**
     * sum[i] 表示前i个数据的和 下标从 1开始
     * 区间 i到j 的和可以看成 sum[j]-sum[i]
     * 因为sum[j] - sum[i] = n*k ,所以sum[j] 和 sum[i] 对k 同余 (可证明)
     * 用hashset存储 余数，匹配时向前推两个下标（长度至少2）存储
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        System.out.println("sum[i]:"+ Arrays.toString(sum));
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= nums.length; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }

        return false;
    }
}
