package leetcode.array;
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
            System.out.println("\n>>>>>>>>>>>当前N:" + i);
            List<List<String>> lists = new N皇后().solveNQueens(i);
            lists.forEach(e -> {
                e.forEach(ei -> {
                    System.out.print(ei + " ");
                });
                System.out.println();
            });
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        for (int i = 0; i < n; i++) {
            //i 行
            List<String> row = new ArrayList<String>();
            for (int j = 0; j < n; j++) {
                //j 列
                if (valid(i, j, n, result)) {
                    row.add(j, "Q");
                }
            }
            result.add(i, row);
        }
        return result;
    }

    //todo  不需要考虑当前行下面的，甚至不需要考虑当前行再放一个，因为一行有且只能放一个
    public boolean valid(int i, int j, int n, List<List<String>> queues) {
        //检测该行该列是否可以放
        if (queues.get(i).contains("Q")) {
            return false;
        }
        for (int x = 0; x < n; x++) {
            if (queues.get(x).get(j).equals("Q")) {
                return false;
            }
        }


        for (int x = i, y = j; x < n && y < n && 2 * j - y > 0; x++, y++) {
            if (queues.get(x).get(y).equals("Q") ||
                    queues.get(x).get(2 * j - y).equals("Q")) {
                return false;
            }
        }

        for (int x = i, y = j; x > 0 && y < n && 2 * j - y > 0; x--, y++) {
            if (queues.get(x).get(y).equals("Q") ||
                    queues.get(x).get(2 * j - y).equals("Q")) {
                return false;
            }
        }
        return true;
    }

}
