package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note:
 * 
 * Elements in a subset must be in non-descending order. The solution set must not contain duplicate
 * subsets.
 * 
 * <pre>
 * For example,
 * If S = [1,2,3], a solution is:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 * </pre>
 */

public class Subsets {
	public List<List<Integer>> subsets(int[] S) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> set = new ArrayList<>();
		// Remember to sort array S as required by the question.
		Arrays.sort(S);
		subsets(res, set, S, 0);
		return res;
	}

	public void subsets(List<List<Integer>> res, List<Integer> sets, int[] S, int index) {
		if (index == S.length) {
			res.add(new ArrayList<Integer>(sets));
		} else {
			sets.add(S[index]);
			subsets(res, sets, S, index + 1);// contains current index element
			sets.remove(sets.size() - 1);
			subsets(res, sets, S, index + 1);
			// do not contain current index element
		}
	}

	// the method below is adding elements and constructing subsets
	public List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		result.add(tmp); // add empty set
		for (int i = 0; i < nums.length; i++) {
			int n = result.size();
			for (int j = 0; j < n; j++) {
				// NOTE: must create a new tmp object, and add element to it.
				tmp = new ArrayList<Integer>(result.get(j));
				tmp.add(nums[i]);
				result.add(tmp);
			}
		}
		return result;
	}

	// Bit operation.
	public List<List<Integer>> subsets3(int[] nums) {
		int elemNum = nums.length;
		int subsetNum = (int) Math.pow(2, elemNum); // Total number of subsets.
		List<List<Integer>> results = new ArrayList<>();
		for (int i = 0; i < subsetNum; i++) {
			results.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < elemNum; i++)
			for (int j = 0; j < subsetNum; j++)
				if (((j >> i) & 0x1) == 1)
					results.get(j).add(nums[i]);
		return results;
	}
}
