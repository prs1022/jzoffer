package leetcode.回溯;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
//
//
// Related Topics 数组 回溯

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2021/9/3 13:35 星期五
 **/
public class N皇后 {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            List<List<String>> lists = new N皇后().solveNQueens(i);
            System.out.println("\n>>>>>>>>>>>当前N:" + i+"--总解法："+lists.size());
            System.out.println(lists);
        }
    }

    public List<List<String>> result = new ArrayList<List<String>>();

    public List<List<String>> solveNQueens(int n) {
        //初始化，Q 是放置的皇后，. 为空
        char[][] array = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = '.';
            }
        }
        set_queue(0, array);

        return result;
    }

    public void set_queue(int row, char[][] queue) {
        int n = queue.length;
        if (row == n) {
            result.add(transfer(queue));
            return;
        }
        for (int col = 0; col < n; col++) {
            //每行恰好放一个，每一列都有可能
            if (!valid(row, col, n, queue)) {
                continue;
            }
            //尝试放
            queue[row][col] = 'Q';
            //进入下一行
            set_queue(row + 1, queue);
            //撤销之前的尝试
            queue[row][col] = '.';
        }
    }

    //不需要考虑当前行下面的，甚至不需要考虑当前行再放一个，因为一行有且只能放一个
    public boolean valid(int i, int j, int n, char[][] queue) {
        //检测该行该列是否可以放
        for (int k = 0; k < n; k++) {
            if (queue[k][j] == 'Q') {
                return false;
            }
        }
        for (int k = i - 1, m = j + 1; k >= 0 && m < n; k--, m++) {
            //检测↗  方向，不需要考虑当前行下面的行
            if (queue[k][m] == 'Q') {
                return false;
            }
        }
        //检测 ↘ 方向，不需要考虑当前行下面的行
        for (int k = i - 1, m = j - 1; k >= 0 && m >= 0; k--, m--) {
            if(queue[k][m]=='Q'){
                return false;
            }
        }

        return true;
    }

    /**
     * 二维数组转列表
     *
     * @param a
     */
    public List<String> transfer(char[][] a) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < a.length; i++) {
            list.add(String.copyValueOf(a[i]));
        }
        return list;
    }

}
