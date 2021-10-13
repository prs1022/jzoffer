package leetcode.easy;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针
// 👍 1228 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2021/9/18 10:30 星期六
 **/
public class 移动零 {
    public static void main(String[] args) {
        int[] nums= new int[]{1,1,0,1,1,1};
//        new 移动零().moveZeroes(nums);
        int count1 = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                ++count1;
            } else {
                result = Math.max(count1, result);
                count1 = 0;
            }
            if (i == nums.length - 1) {
                result = Math.max(count1, result);
            }
        }
        System.out.println(result);
    }



    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[j]!=0) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
            }

        }
    }
}
