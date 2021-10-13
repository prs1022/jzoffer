package leetcode.array;

/**
 * @author rensong.pu
 * @date 2021/8/9 16:04 星期一
 **/
public class 盛最多水的容器 {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
    public static int maxArea(int[] height) {
        //准备两个指针，一个从头(数组)开始，一个从尾部开始
        // 确认一个规律事实：如果指针i，和尾部指针j 所构成的面积是S(i,j) = min(h(i),h(j)) * (j-i)  [确保j>i]
        // 即 短板效应，而移动的时候每次只移动短的那块，因为短的可能导致面积变大，而长的是不会影响min的值，而j-i一定小，所以移动短板合适
        int s = 0;
        for (int i = 0, j = height.length- 1; j > i; ) {
            int min_x = Math.min(height[j], height[i]);
            //计算面积
            int tmp = min_x * (j - i);
            s = Math.max(s, tmp);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return s;
    }
}
