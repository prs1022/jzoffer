package leetcode.回溯;
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
//
//
// 示例 3：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
//
//
//
//
// 提示：
//
//
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
// Related Topics 数组 回溯 矩阵
// 👍 1026 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2021/9/16 11:08 星期四
 **/
public class 单词搜索 {

    // 搜索的四个方向，这种技巧比较常用
    public int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "SEE";
        System.out.println(word + "是否存在:" + new 单词搜索().exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        if (board.length <= 0 || board[0].length <= 0) {
            return false;
        }
        //定义一个与board等大的空间，用于记录是否访问过，作为回溯的依据
        // 这种做法是会浪费空间，也可以将访问过的board临时替换为*，回溯的时候再恢复
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 外层： 遍历board 所有元素，找到第一个与word相同的元素，然后进入递归流程，记得打上标记
                if (board[i][j] == word.charAt(0)) {
                    //匹配到第一个字符，标记
                    visited[i][j] = true;
                    //进入递归
                    if (dfs(i, j, board, visited, word, 1)) {
                        return true;
                    } else {
                        //回溯
                        visited[i][j] = false;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 深度优先遍历
     *
     * @param i
     * @param j
     * @param board
     * @param visited
     * @param word
     * @param k
     * @return
     */
    public boolean dfs(int i, int j, char[][] board, boolean[][] visited, String word, int k) {
        if (k == word.length()) {
            return true;
        }

        // 从 i,j出发 向上下左右试探，看看是否能匹配word 的下一个字母
        // 如果匹配，则带着元素 进入下一个递归
        for (int p = 0; p < directions.length; p++) {
            int new_i = i + directions[p][0];
            int new_j = j + directions[p][1];
            if (inArea(new_i, new_j, board.length, board[0].length)) {
                if (board[new_i][new_j] == word.charAt(k) && !visited[new_i][new_j]) {
                    // 标记
                    visited[new_i][new_j] = true;
                    // 重要！！ 不能直接return dfs，否则
                    if (dfs(new_i, new_j, board, visited, word, k + 1)) {
                        return true;
                    } else {
                        visited[new_i][new_j] = false;
                    }
                }
            }
        }
        return false;
    }

    public boolean inArea(int i, int j, int r, int c) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

}
