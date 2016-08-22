package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 * Note: Elements in a subset must be in non-descending order. The solution set must not contain
 * duplicate subsets.
 * 
 * <pre>
 * For example,
 * If S = [1,2,2], a solution is:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * </pre>
 */

public class SubsetsII {
	/**
	 * <pre>
	 * For [1, 2, 3], order:
	 * []
	 * [1]
	 * [2] [1, 2]
	 * [3] [1, 3] [2, 3] [1, 2, 3]
	 * For [1, 2, 2], order:
	 * []
	 * [1]
	 * [2] [1, 2]
	 * (start = size) [2, 2] [1, 2, 2]
	 * </pre>
	 */
	public List<List<Integer>> subsetsWithDup(int[] num) {
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<Integer>());
		Arrays.sort(num);
		int start = 0;
		for (int i = 0; i < num.length; i++) {
			int size = res.size();
			for (int j = start; j < size; j++) {
				ArrayList<Integer> sub = new ArrayList<Integer>(res.get(j));
				sub.add(num[i]);
				res.add(sub);
			}
			if (i < num.length - 1 && num[i + 1] == num[i])
				start = size;
			else
				start = 0;
		}
		return res;
	}

	// Solution 2
	public List<List<Integer>> subsetsWithDup2(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> each = new ArrayList<>();
		helper(res, each, 0, nums);
		return res;
	}

	public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
		if (pos <= n.length) {
			res.add(each);
		}
		int i = pos;
		while (i < n.length) {
			each.add(n[i]);
			helper(res, new ArrayList<>(each), i + 1, n);
			each.remove(each.size() - 1);
			i++;
			while (i < n.length && n[i] == n[i - 1]) {
				i++;
			}
		}
		return;
	}

	/**
	 * <pre>
	 * For [1, 2, 3],
	 * order: [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
	 * </pre>
	 */
	public List<List<Integer>> subsetsWithDup3(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<Integer>());
		int begin = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i] != nums[i - 1])
				begin = 0;
			int size = result.size();
			for (int j = begin; j < size; j++) {
				List<Integer> cur = new ArrayList<>(result.get(j));
				cur.add(nums[i]);
				result.add(cur);
			}
			begin = size;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] num = { 1, 2, 3 };
		SubsetsII test = new SubsetsII();
		test.subsetsWithDup2(num);
	}
}
