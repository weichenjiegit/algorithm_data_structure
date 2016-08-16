package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Given an array S of n integers,
 * are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order.
 * (ie, a ? b ? c ? d)
 * The solution set must not contain duplicate quadruplets.
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * A solution set is:
 * (-1,  0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2,  0, 0, 2)
 */
public class FourSum {
	// Solution 1. Based on three sum, O(n^3)
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (num.length < 4)
			return res;
		ArrayList<Integer> inner;
		Arrays.sort(num);
		int first, second, left, right, sum;
		for (int i = 0; i < num.length - 2;) {
			first = num[i];
			for (int j = i + 1; j < num.length;) {
				second = num[j];
				left = j + 1;
				right = num.length - 1;
				while (left < right) {
					sum = first + second + num[left] + num[right];
					if (sum == target) {
						inner = new ArrayList<Integer>();
						inner.add(first);
						inner.add(second);
						inner.add(num[left]);
						inner.add(num[right]);
						res.add(inner);
						int temp = num[left];
						while (left < right && temp == num[left])
							left++;
						temp = num[right];
						while (left < right && temp == num[right])
							right--;
					} else if (sum > target) {
						right--;
					} else
						left++;
				}
				while (++j < num.length && num[j] == second)
					;
			}
			while (++i < num.length && num[i] == first)
				;
		}
		return res;
	}

	// Solution 2.
	// Second solution. Find sums for each two elements,
	// then find two 2-sum equal to target.
	// O(n^2 * lgn).
	// For O(n^2 lgn), need a sorting method for two_sums,
	// and use similar O(n) method to find a match.

	// Solution 3
	// Need a sorting method for class two_sums. Too much work.
	// Passed judge small, failed on large judge. O( ((n-1)n)^2 ).
	public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> inner;
		HashSet<ArrayList<Integer>> hashset = new HashSet<ArrayList<Integer>>();
		if (num.length < 4)
			return res;
		int n = num.length, k = 0;
		int len = (n - 1) * n / 2;
		Sums[] two_sums = new Sums[len];
		int[] result_set = new int[4];
		for (int i = 0; i < num.length; i++) {// rows
			for (int j = i + 1; j < num.length; j++) {// columns
				two_sums[k++] = new Sums(num[i] + num[j], i, j);
			}
		}

		for (int i = 0; i < two_sums.length; i++) {// rows
			for (int j = i + 1; j < two_sums.length; j++) {// columns
				if (two_sums[i].value + two_sums[j].value == target
						&& two_sums[i].row_index != two_sums[j].row_index
						&& two_sums[i].column_index != two_sums[j].column_index
						&& two_sums[i].row_index != two_sums[j].column_index
						&& two_sums[i].column_index != two_sums[j].row_index) {
					// skip duplicates
					result_set[0] = num[two_sums[i].row_index];
					result_set[1] = num[two_sums[i].column_index];
					result_set[2] = num[two_sums[j].row_index];
					result_set[3] = num[two_sums[j].column_index];
					Arrays.sort(result_set);
					inner = new ArrayList<Integer>();
					inner.add(result_set[0]);
					inner.add(result_set[1]);
					inner.add(result_set[2]);
					inner.add(result_set[3]);
					hashset.add(inner);// results still contains duplicates
				}
			}
		}
		Iterator<ArrayList<Integer>> it = hashset.iterator();
		while (it.hasNext()) {
			res.add(it.next());
		}
		return res;
	}

	private class Sums {
		int value;
		int row_index;
		int column_index;

		Sums(int val, int r_index, int c_index) {
			value = val;
			row_index = r_index;
			column_index = c_index;
		}
	}
}
