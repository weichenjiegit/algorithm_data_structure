package leetcode;

import java.util.Arrays;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its
 * sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed
 * integer range.
 *
 */
public class MaximumGap {
	// Solution 1
	// Use radix sort
	// https://www.cs.usfca.edu/~galles/visualization/RadixSort.html animated explanation
	public int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		// m is the maximal number in nums
		int m = nums[0];
		for (int i = 1; i < nums.length; i++) {
			m = Math.max(m, nums[i]);
		}
		int exp = 1; // 1, 10, 100, 1000 ...
		int R = 10; // 10 digits
		int[] aux = new int[nums.length];
		while (m / exp > 0) { // Go through all digits from LSB to MSB
			int[] count = new int[R];
			for (int i = 0; i < nums.length; i++) {
				count[(nums[i] / exp) % 10]++;
			}
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];
			}
			for (int i = nums.length - 1; i >= 0; i--) {
				aux[--count[(nums[i] / exp) % 10]] = nums[i];
			}
			for (int i = 0; i < nums.length; i++) {
				nums[i] = aux[i];
			}
			exp *= 10;
		}
		int max = 0;
		for (int i = 1; i < aux.length; i++) {
			max = Math.max(max, aux[i] - aux[i - 1]);
		}
		return max;
	}

	// Solution 2
	// Create n - 1 buckets, put numbers into the bucket
	// Only need to calculate the gap between successive buckets
	public int maximumGap2(int[] num) {
		if (num == null || num.length < 2)
			return 0;
		// get the max and min value of the array
		int min = num[0];
		int max = num[0];
		for (int i : num) {
			min = Math.min(min, i);
			max = Math.max(max, i);
		}
		// the minimum possible gap, ceiling of the integer division
		int gap = (int) Math.ceil((double) (max - min) / (num.length - 1));
		int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
		int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
		Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
		Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
		// put numbers into buckets
		for (int i : num) {
			if (i == min || i == max)
				continue;
			int idx = (i - min) / gap; // index of the right position in the buckets
			bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
			bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
		}
		// scan the buckets for the max gap
		int maxGap = Integer.MIN_VALUE;
		int previous = min;
		for (int i = 0; i < num.length - 1; i++) {
			if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
			    // empty bucket
			    continue;
			// min value minus the previous value is the current gap
			maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
			// update previous bucket value
			previous = bucketsMAX[i];
		}
		maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
		return maxGap;
	}
}
