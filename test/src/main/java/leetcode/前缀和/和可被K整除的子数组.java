package leetcode.前缀和;
//给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
//
//
//
// 示例：
//
// 输入：A = [4,5,0,-2,-3,1], K = 5
//输出：7
//解释：
//有 7 个子数组满足其元素之和可被 K = 5 整除：
//[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
//
//
//
//
// 提示：
//
//
// 1 <= A.length <= 30000
// -10000 <= A[i] <= 10000
// 2 <= K <= 10000
//
// Related Topics 数组 哈希表 前缀和 👍 306 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2021/9/29 16:19 星期三
 **/
public class 和可被K整除的子数组 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,9};
        int k = 2;
        final int i = new 和可被K整除的子数组().subarraysDivByK(nums, k);
        System.out.println("\n"+i);
    }

    /**
     * 同余定理
     * (sumi-sumj)%k==0 等价于sumi%k == sumj%k
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK(int[] nums, int k) {
        int pre_sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pre_sum += nums[i];
            System.out.print(pre_sum+"\t");
            if (map.containsKey(pre_sum % k)) {
                // 找到了和 sumi 对k 同余 的sumj ，记录次数
                count += map.get(pre_sum % k);
            }
            map.put(pre_sum % k, map.getOrDefault(pre_sum % k, 0) + 1);
        }
        return count;
    }
}
