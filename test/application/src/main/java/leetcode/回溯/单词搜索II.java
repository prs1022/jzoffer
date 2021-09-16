package leetcode.回溯;
//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。
//
//
//
// 示例 1：
//
//
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
//
//
// 示例 2：
//
//
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
//
//
//
//
// 提示：
//
//
// m == board.length
// n == board[i].length
// 1 <= m, n <= 12
// board[i][j] 是一个小写英文字母
// 1 <= words.length <= 3 * 104
// 1 <= words[i].length <= 10
// words[i] 由小写英文字母组成
// words 中的所有字符串互不相同
//
// Related Topics 字典树 数组 字符串 回溯 矩阵
// 👍 454 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2021/9/16 11:08 星期四
 **/
public class 单词搜索II {

    // 搜索的四个方向，这种技巧比较常用
    public int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] word = new String[]{"oath", "pea", "eat", "rain"};
        final List<String> words = new 单词搜索II().findWords(board, word);
        System.out.println(words);
    }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if(exist(board, words[i])){
                result.add(words[i]);
            }
        }
        return result;
    }

    public boolean exist(char[][] board, String word) {
        if (board.length <= 0 || board[0].length <= 0) {
            return false;
        }
        //定义一个与board等大的空间，用于记录是否访问过，作为回溯的依据
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
