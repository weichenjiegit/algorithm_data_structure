package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique
 * permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: [1,1,2], [1,2,1], and [2,1,1].
 */
public class PermutationsII {
	// Solution 1
	// Use an array to mark appearance
	public List<List<Integer>> permuteUnique(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> current = new ArrayList<Integer>();
		boolean[] visited = new boolean[num.length];
		permute(result, current, num, visited);
		return result;
	}

	private void permute(List<List<Integer>> result, List<Integer> current, int[] num, boolean[] visited) {
		if (current.size() == num.length) {
			result.add(new ArrayList<Integer>(current));
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				if (i > 0 && num[i] == num[i - 1] && visited[i - 1]) {
					return;
				}
				visited[i] = true;
				current.add(num[i]);
				permute(result, current, num, visited);
				current.remove(current.size() - 1);
				visited[i] = false;
			}
		}
	}

	// Solution 2
	// Check duplication on the fly
	public List<List<Integer>> permuteUnique2(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		permuteUnique(result, nums, 0);
		return result;
	}

	private void permuteUnique(List<List<Integer>> result, int[] nums, int idx) {
		if (idx == nums.length) {
			ArrayList<Integer> arrayList = new ArrayList<>();
			for (int num : nums) {
				arrayList.add(num);
			}
			result.add(arrayList);
		}
		for (int i = idx; i < nums.length; i++) {
			if (!isDuplicate(nums, idx, i)) {
				swap(nums, idx, i);
				permuteUnique(result, nums, idx + 1);
				swap(nums, idx, i);
			}
		}
	}

	private boolean isDuplicate(int[] nums, int idx, int i) {
		for (int i1 = idx; i1 < i; i1++) {
			if (nums[i1] == nums[i]) {
				return true;
			}
		}
		return false;
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// Solution 3
	// Count the number of each number.
	public ArrayList<ArrayList<Integer>> res;
	public ArrayList<Integer> list;
	public int[] temp;
	public ArrayList<Integer> value;
	public ArrayList<Integer> counter;

	public ArrayList<ArrayList<Integer>> permuteUnique3(int[] num) {
		res = new ArrayList<ArrayList<Integer>>();
		list = new ArrayList<Integer>();
		value = new ArrayList<Integer>();
		counter = new ArrayList<Integer>();
		Arrays.sort(num);
		int val = num[0];
		int count = 1;
		for (int i = 1; i < num.length; i++) {
			if (val == num[i])
				count++;
			else {
				value.add(val);
				counter.add(count);
				val = num[i];
				count = 1;
			}
		}
		value.add(val);
		counter.add(count);
		temp = new int[num.length];
		perm(temp, 0);
		return res;
	}

	public void perm(int[] temp, int cur) {
		if (cur == temp.length) {
			for (int i = 0; i < temp.length; i++)
				list.add(temp[i]);
			res.add(list);
			list = new ArrayList<Integer>();
		} else {
			for (int i = 0; i < value.size(); i++) {
				if (counter.get(i) > 0) {
					counter.set(i, counter.get(i) - 1);
					temp[cur] = value.get(i);
					perm(temp, cur + 1);
					counter.set(i, counter.get(i) + 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] num = { 2, 2, 1, 1 };
		PermutationsII test = new PermutationsII();
		test.permuteUnique(num);
	}
}
