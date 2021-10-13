package leetcode.think;

/**
 * 大学蓝桥杯比赛遇到的一道题，当时懵逼没做出来，如今豁然开朗，真的是多做题才能做到心中有数，
 * 否则看啥都是慌慌的，蒙蒙的，毕竟咱不是天才啊。。
 * @author rensong.pu
 * @date 2021/9/13 16:18 星期一
 **/
public class 所有蚂蚁掉下来的最后一刻 {
    //关键在于蚂蚁遇到 立马返回 不算时间，可以当做是自己的附身继续前进，所以不考虑返回，直接往前，计算最后一只蚂蚁落下的时间。
    public int getLastMoment(int n, int[] left, int[] right) {
        int max = 0;
        for (int i = 0; i < left.length; i++) {
            max = Math.max(max, left[i]);
        }

        for (int j = 0; j < right.length; j++) {
            max = Math.max(max, n-right[j]);
        }

        return max;
    }
}
