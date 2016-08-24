package leetcode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 *
 */
public class FindMinimumInRotatedSortedArray {

	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int length = nums.length;
		if (length == 1) {
			return nums[0];
		}
		int low = 0, high = nums.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] < nums[high])
			    // the mininum is in the left part
			    high = mid;
			else // (nums[mid] > nums[high], the mininum is in the right part
			    low = mid + 1;
		}
		return nums[low];
	}
}
