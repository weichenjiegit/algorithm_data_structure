package leetcode;

import java.util.PriorityQueue;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 
 * Example:
 * 
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * 
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * 
 * Note: You may assume all input has valid answer.
 * 
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WigleSortII {
	/**
	 * (1) elements smaller than the 'median' are put into the last even slots
	 * 
	 * (2) elements larger than the 'median' are put into the first odd slots
	 * 
	 * (3) the medians are put into the remaining slots
	 */
	public void wiggleSort(int[] nums) {
		int median = findKthLargest(nums, (nums.length + 1) / 2);
		int n = nums.length;

		int left = 0, i = 0, right = n - 1;

		while (i <= right) {
			if (nums[newIndex(i, n)] > median) {
				swap(nums, newIndex(left++, n), newIndex(i++, n));
			} else if (nums[newIndex(i, n)] < median) {
				swap(nums, newIndex(right--, n), newIndex(i, n));
			} else {
				i++;
			}
		}

	}

	private int newIndex(int index, int n) {
		// (n | 1) calculates the nearest odd that is not less than n.
		return (1 + 2 * index) % (n | 1);
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// Max heap
	public int findKthLargest(int[] nums, int k) {
		final PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int val : nums) {
			pq.offer(val);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}
}
