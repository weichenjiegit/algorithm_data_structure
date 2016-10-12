package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are in the inclusive range [lower,
 * upper], return its missing ranges.
 * 
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74",
 * "76->99"].
 */
public class MissingRanges {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
		for (int num : nums) {
			// if num is MIN, num - 1 will be MAX
			if (num == Integer.MIN_VALUE) {
				lower = num + 1;
				continue;
			}
			if (lower < num - 1)
				res.add(lower + "->" + (num - 1));
			else if (lower == num - 1)
				res.add(String.valueOf(lower));

			lower = num + 1;
		}

		// if the last num is MAX, num + 1 will be MIN
		if (lower == Integer.MIN_VALUE)
			return res;

		if (lower == upper)
			res.add(String.valueOf(lower));
		else if (lower < upper)
			res.add(lower + "->" + upper);

		return res;
	}

	public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
		// the next number we need to find
		int next = lower;
		for (int num : nums) {
			// not within the range yet
			if (num < next)
				continue;
			// continue to find the next one
			if (num == next) {
				next++;
				continue;
			}
			// get the missing range string format
			res.add(getRange(next, num - 1));
			// now we need to find the next number
			next = num + 1;
		}

		// do a final check
		// if last num == Integer.MAX_VALUE, then next = Integer.MIN_VALUE
		if (next != Integer.MIN_VALUE && next <= upper)
			res.add(getRange(next, upper));
		return res;
	}

	private String getRange(int n1, int n2) {
		return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
	}
}
