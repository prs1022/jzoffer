package leetcode.array;//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 示例 1：
//
//
//
//
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//
//
// 示例 2：
//
//
//输入：height = [4,2,0,3,2,5]
//输出：9
//
//
//
//
// 提示：
//
//
// n == height.length
// 0 <= n <= 3 * 104
// 0 <= height[i] <= 105
//
// Related Topics 栈 数组 双指针 动态规划 单调栈
// 👍 2547 👎 0
// 空间复杂度 O(1)，时间复杂度O(mn) m为数组长度，n为数组的最大值，这个解法会导致超时，可能是n不可控，过大导致

//当前时间:2021-08-05 16:15:57

//leetcode submit region begin(Prohibit modification and deletion)
class 接雨水 {
    public int trap(int[] height) {
        int result = 0;
        int maxheight = getMaxHeight(height);
        for (int i = 1; i <= maxheight; i++) { //i 记录第几层，从下往上开始算
            boolean tempflag = false;//是否更新temp
            int temp = 0;
            for (int j = 0; j < height.length; j++) {
                if (tempflag && height[j] < i) {
                    temp++;
                }
                if (height[j] >= i) {
                    //开始计算蓄水
                    result = result + temp;
                    tempflag = true;
                    temp = 0;
                }
            }
        }
        return result;
    }

    public int getMaxHeight(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }
}


// 时间复杂度O(n^2)，空间复杂度O(1)
class Solution2 {
    public int trap(int[] height) {
        int result = 0;
        //计算每一列能蓄多少，蓄多少由左边最高的，和右边最高的柱子决定，而多少由两边最短的决定（木桶效应）
        for (int i = 1; i < height.length - 1; i++) {
            //左边和右边的柱子不蓄水,i从1到len-2遍历
            int max_left = 0;
            for (int j = 0; j < i; j++) {
                //找出左边最高的
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            for (int k = i + 1; k < height.length; k++) {
                if(height[k] > max_right) {
                    max_right = height[k];
                }
            }
            //取两者最短
            int minner = Math.min(max_right,max_left);
            if(height[i]<minner){
                result += minner-height[i];
            }
        }
        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
