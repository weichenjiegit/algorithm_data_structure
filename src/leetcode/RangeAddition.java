package leetcode;

/**
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * 
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each
 * element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * 
 * Return the modified array after all k operations were executed.
 */

public class RangeAddition {
	// Solution O(k + n)
	// Just store every start index for each value and at end index plus one minus it
	public int[] getModifiedArray(int length, int[][] updates) {
		int[] res = new int[length];
		for (int[] update : updates) {
			int value = update[2];
			int start = update[0];
			int end = update[1];

			res[start] += value;

			if (end < length - 1)
				res[end + 1] -= value;
		}

		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += res[i];
			res[i] = sum;
		}

		return res;
	}
}
