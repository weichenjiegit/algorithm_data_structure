package leetcode;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <=
 * nums[3]....
 * 
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {
	/**
	 * The final sorted nums needs to satisfy two conditions:
	 * 
	 * 1. If i is odd, then nums[i] >= nums[i - 1];
	 * 
	 * 2. If i is even, then nums[i] <= nums[i - 1].
	 * 
	 * The code is just to fix the orderings of nums that do not satisfy 1 and 2.
	 */
	public void wiggleSort(int[] nums) {
		for (int i = 0; i < nums.length; i++)
			if (i % 2 == 1) { // odd index position
				if (nums[i - 1] > nums[i])
					swap(nums, i);
			} else if (i != 0 && nums[i - 1] < nums[i])
				swap(nums, i);
	}

	private void swap(int[] nums, int i) {
		int tmp = nums[i];
		nums[i] = nums[i - 1];
		nums[i - 1] = tmp;
	}
}
