package leetcode.array;
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//
// 示例 2:
//
//
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//]
//
//
//
// 提示:
//
//
// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30
//
// Related Topics 数组 回溯 👍 699 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2021/9/30 14:50 星期四
 **/
public class 组合总和II {
    public List<Integer> path = new ArrayList<>();
    public List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        final List<List<Integer>> lists = new 组合总和II().combinationSum2(candidates, target);
        System.out.println(Arrays.toString(lists.toArray()));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, 0, target);
        return res;
    }

    public void dfs(int[] candidates, int sum, int pos, int target) {

        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            if (i>pos && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (sum + candidates[i] <= target) {
                path.add(candidates[i]);
                dfs(candidates, sum + candidates[i], i + 1, target);
                path.remove(path.size() - 1);
            }

        }

    }

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(res, combination, 0, 0, candidates, target);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> combination, int sum, int pos, int[] candidates, int target) {
        //路径上节点和等于target，此路径满足条件，加入res
        if (sum == target) {
            res.add(new ArrayList<>(combination));
            return;
        }
        //路径上节点和大于target，由于节点均大于1，因此此路径无法满足条件，剪枝
        if (sum > target) {
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            //同一层数值相同的节点搜索出满足条件的集合必然是第一个该数值节点的子集
            if (i > pos && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (sum + candidates[i] <= target) {
                combination.add(candidates[i]);
                backtracking(res, combination, sum + candidates[i], i + 1, candidates, target);
                combination.remove(combination.size() - 1);
            }
        }
    }


}
