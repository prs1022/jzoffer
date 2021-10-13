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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题解中 在dfs基础上 增加了 字典树 优化
 * @author rensong.pu
 * @date 2021/9/16 11:08 星期四
 **/
public class 单词搜索IInew {

    // 搜索的四个方向，这种技巧比较常用
    public int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] word = new String[]{"oath", "pea", "eat", "rain"};
        final List<String> words = new 单词搜索IInew().findWords(board, word);
        System.out.println(words);
    }


    // 上下左右移动的方向
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        // 结果集，去重
        Set<String> resultSet = new HashSet<>();

        // 构建字典树
        TrieNode root = buildTrie(words);

        int m = board.length;
        int n = board[0].length;
        // 记录某个下标是否访问过
        boolean[][] visited = new boolean[m][n];
        // 记录沿途遍历到的元素
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 从每个元素开始遍历
                dfs(resultSet, result, board, i, j, root, visited);
            }
        }

        // 题目要求返回List
        return new ArrayList<>(resultSet);
    }

    private void dfs(Set<String> resultSet, StringBuilder result, char[][] board,
                     int i, int j, TrieNode node, boolean[][] visited) {
        // 判断越界，或者访问过，或者不在字典树中，直接返回
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || node.children[board[i][j] - 'a'] == null) {
            return;
        }

        // 记录当前字符
        result.append(board[i][j]);

        // 如果有结束字符，加入结果集中
        if (node.children[board[i][j] - 'a'].isEnd) {
            resultSet.add(result.toString());
        }

        // 记录当前元素已访问
        visited[i][j] = true;

        // 按四个方向去遍历
        for (int[] dir : dirs) {
            dfs(resultSet, result, board, i + dir[0], j + dir[1], node.children[board[i][j] - 'a'], visited);
        }

        // 还原状态
        visited[i][j] = false;
        result.deleteCharAt(result.length() - 1);
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            char[] arr = word.toCharArray();
            TrieNode curr = root;
            for (char c : arr) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.isEnd = true;
        }
        return root;
    }

    class TrieNode {
        // 记录到这个节点是否是一个完整的单词
        boolean isEnd = false;
        // 孩子节点，题目说了都是小写字母，所以用数组，否则可以用HashMap替换
        TrieNode[] children = new TrieNode[26];
    }
}
