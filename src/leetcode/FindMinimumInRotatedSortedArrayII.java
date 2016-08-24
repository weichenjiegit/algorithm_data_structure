package leetcode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 *
 */
public class FindMinimumInRotatedSortedArrayII {

	public int findMin(int[] nums) {
		int low = 0;
		int high = nums.length - 1;
		int mid = 0;
		while (low < high) {
			mid = low + (high - low) / 2;
			if (nums[mid] < nums[high]) {
				high = mid;
			} else if (nums[mid] > nums[high]) {
				low = mid + 1;
			} else { // when num[mid] and num[high] are same
				high--;
			}
		}
		return nums[low];
	}
}
