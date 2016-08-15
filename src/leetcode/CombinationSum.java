package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a set of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, ... ak) must be in non-descending order.
 * (ie, a1 <= a2 <= ... <= ak).
 * The solution set must not contain duplicate combinations.
 * 
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 */
public class CombinationSum {
	// DFS solution
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> cur = new ArrayList<Integer>();
		Arrays.sort(candidates);
		find(res, cur, candidates, 0, target);
		return res;
	}

	public void find(List<List<Integer>> res, List<Integer> cur, int[] candidates, int index, int target) {
		if (target == 0)
			res.add(new ArrayList<Integer>(cur));
		else {
			for (int i = index; i < candidates.length; i++) {
				if (target >= candidates[i]) {
					cur.add(candidates[i]);
					find(res, cur, candidates, i, target - candidates[i]);
					cur.remove(cur.size() - 1);
				}
			}
		}
	}
}
