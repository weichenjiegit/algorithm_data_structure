package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations. By listing and labeling all of
 * the permutations in order, We get the following sequence (ie, for n = 3):
 * 
 * "123" "132" "213" "231" "312" "321"
 *
 * Given n and k, return the kth permutation sequence. Note: Given n will be between 1 and 9
 * inclusive.
 */
public class PermutationSequence {
	// Solution 1 Based on nextPermutation
	public String getPermutation(int n, int k) {
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = i + 1;
		}
		for (int i = 1; i < k; i++) {
			num = nextPermutation(num);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(num[i]);
		return sb.toString();
	}

	public int[] nextPermutation(int[] num) {
		int len = num.length;
		if (len <= 1)
			return num;
		for (int i = len - 1; i > 0; i--) {
			// Search the first decreasing number
			if (num[i - 1] < num[i]) {
				// Search the target to be swapped:， min value in num[i...len - 1] that > num[i - 1]
				int j = i;
				while (j < len && num[j] > num[i - 1])
					j++;
				j--;
				int tmp = num[j];
				num[j] = num[i - 1];
				num[i - 1] = tmp;
				Arrays.sort(num, i, len);
				return num;
			}
		}
		Arrays.sort(num);
		return num;
	}

	// Solution 2
	public String getPermutation2(int n, int k) {
		List<Integer> num = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++)
			num.add(i);
		int[] fact = new int[n]; // factorial
		fact[0] = 1;
		for (int i = 1; i < n; i++)
			fact[i] = i * fact[i - 1];
		// factorial[] = {1, 1, 2, 6, 24, ... n!}
		k = k - 1;
		StringBuilder sb = new StringBuilder();
		for (int i = n; i > 0; i--) {
			int ind = k / fact[i - 1];
			k = k % fact[i - 1];
			sb.append(num.get(ind));
			num.remove(ind);
		}
		return sb.toString();
	}
}
