package leetcode.array;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author rensong.pu
 * @date 2021/8/12 14:28 星期四
 **/
public class 四数之和 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2,2,2,2};
        int target = 8;
        final List<List<Integer>> lists = new 四数之和().fourSum(nums, target);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }
    }

    /**
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * stdout:
     *
     * @param nums
     * @return
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);

        // 遍历
        for (int i = 0; i < nums.length; i++) {
            // if (nums[i] > target) break;
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 对第二个数进行循环
            for (int j = i + 1; j < nums.length; j++) {
                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                // 定义指针
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 在找到一个数组后进行去重
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // 移动指针
                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }

}
