package leetcode;

import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. If it does
 * not exist, return the maximum number. The time complexity must be in O(n).
 * 
 * Input: [2, 2, 3, 1]
 * 
 * Output: 1
 * 
 * Explanation: Note that the third maximum here means the third maximum distinct number. Both
 * numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {
	public int thirdMax(int[] nums) {
		Integer max1 = null;
		Integer max2 = null;
		Integer max3 = null;
		for (Integer n : nums) {
			if (n.equals(max1) || n.equals(max2) || n.equals(max3))
				continue;
			if (max1 == null || n > max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (max2 == null || n > max2) {
				max3 = max2;
				max2 = n;
			} else if (max3 == null || n > max3) {
				max3 = n;
			}
		}
		return max3 == null ? max1 : max3;
	}

	public int thirdMax2(int[] nums) {

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {

			if (max < nums[i])
				max = nums[i];

			if (!minHeap.contains(nums[i]))
				minHeap.offer(nums[i]);

			if (minHeap.size() > 3)
				minHeap.remove();
		}

		return minHeap.size() <= 2 ? max : minHeap.peek();
	}
}
