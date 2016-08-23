package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
 * unique triplets in the array which gives the sum of zero.
 * 
 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ? b ? c) The solution
 * set must not contain duplicate triplets.
 * 
 * For example, given array S = {-1 0 1 2 -1 -4}, A solution set is: (-1, 0, 1) (-1, -1, 2)
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> res = new ArrayList<>();
		if (num.length < 3)
			return res;
		Arrays.sort(num);
		int origin, left, right, sum, i = 0;
		while (i < num.length - 2) {
			if (num[i] > 0) // already larger than 0
				break;
			origin = num[i];
			left = i + 1;
			right = num.length - 1;
			while (left < right) {
				sum = origin + num[left] + num[right];
				if (sum == 0) {
					List<Integer> inner = new ArrayList<Integer>(Arrays.asList(origin, num[left], num[right]));
					res.add(inner);
					int temp = num[left];
					while (left < right && temp == num[left])
						// skip same number
						left++;
					temp = num[right];
					while (left < right && temp == num[right])
						// skip same number
						right--;
				} else if (sum > 0) {
					right--;
				} else
					left++;
			}
			while (++i < num.length && num[i] == origin)
				;// skip same origins
		}
		return res;
	}
}
