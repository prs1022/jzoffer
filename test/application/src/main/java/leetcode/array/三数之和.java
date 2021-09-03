package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2021/8/12 14:28 星期四
 **/
public class 三数之和 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 0, 2, 3, -1};
        final List<List<Integer>> lists = new 三数之和().threeSum(nums);
        // -4 -1 -1 0 1 2
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }
    }

    /**
     * 测试用例:[-1,0,1,2,-1,-4,-2,-3,3,0,4]
     * 测试结果:[[0,4,-4],[1,3,-4],[1,2,-3],[0,2,-2],[0,1,-1]]
     * 期望结果:[[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]]
     * stdout:
     *
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        for (int k = 0, i = 1, j = nums.length - 1; k < nums.length - 2; k++, i = k + 1, j = nums.length - 1) {
            // 遍历一遍，左右指针 i,j
            if (nums[k] > 0) {
                //如果最小的数大于0 了，三个数之和都大于0
                continue;
            }
            if (k > 0 && nums[k - 1] == nums[k]) {
                continue;
            }
            System.out.print(k + "\t");

            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    result.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k]}));
                    // 同时移动，并略过重复
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }


            }

        }
        return result;
    }


    public List<List<Integer>> threeSum(int[] nums, int target) {
        if (nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + " ");
//        }
//        System.out.println("======");
        for (int k = 0; k < nums.length - 2; k++) {

            int i = 1, j = nums.length - 1;
            // 遍历一遍，左右指针 i,j
            if (nums[k] > 0) {
                //如果最小的数大于0 了，三个数之和都大于0
                continue;
            }
            if (k > 0 && nums[k - 1] == nums[k]) {
                continue;
            }

            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > target) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {

                    result.add(new ArrayList(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k]})));
                    // 同时移动，并略过重复
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }


            }

        }
        return result;
    }

}
