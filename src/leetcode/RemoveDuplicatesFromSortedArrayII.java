package leetcode;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, and nums is now [1,1,2,2,3].
 */
public class RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates(int[] nums) {
		if (nums.length <= 2)
			return nums.length;
		int counter = 0;
		int pos = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] != nums[i]) {
				counter = 1;
				nums[pos++] = nums[i];
			} else if (counter == 1) {
				counter++;
				nums[pos++] = nums[i];
			}
		}
		return pos;
	}
}
