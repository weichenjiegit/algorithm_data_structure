package leetcode;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate
 * number, find the duplicate one.
 * 
 * Note:
 * 
 * You must not modify the array (assume the array is read only).
 * 
 * You must use only constant, O(1) extra space.
 * 
 * Your runtime complexity should be less than O(n ^ 2).
 * 
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int low = 1, high = nums.length - 1;
		while (low <= high) {
			int mid = (int) (low + (high - low) / 2);
			int count = 0;
			for (int a : nums) {
				if (a <= mid)
					count++;
			}
			if (count <= mid)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}

	// Similar to detect a circle in a linked list.
	public int findDuplicate2(int[] nums) {
		if (nums.length > 1) {
			int slow = nums[0];
			int fast = nums[nums[0]];
			while (slow != fast) {
				slow = nums[slow];
				fast = nums[nums[fast]];
			}

			fast = 0;
			while (fast != slow) {
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		return -1;
	}
}
