package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, ... ak) must be in non-descending order.
 * (ie, a1 <= a2 <= ... <= ak).
 * 
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 */
public class CombinationSumII {
	// DFS
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
					if(i > index && candidates[i] == candidates[i-1]){
						continue; // skip duplicates
					}
					cur.add(candidates[i]);
					find(res, cur, candidates, i + 1, target - candidates[i]); // diff here
					cur.remove(cur.size() - 1);
				}
			}
		}
	}
}
