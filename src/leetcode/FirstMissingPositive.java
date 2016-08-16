package leetcode;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2. Your algorithm should run in O(n)
 * time and uses constant space.
 */
public class FirstMissingPositive {
	// Solution 1
	// sign of A[cur - 1], if negative, cur exists in A。
	public int firstMissingPositive(int[] A) {
		int len = A.length;
		if (len < 1)
			return 1;
		for (int i = 0; i < len; i++) {
			if (A[i] < 0)
				A[i] = 0;
		}
		for (int i = 0; i < len; i++) {
			int cur = Math.abs(A[i]);
			if (cur <= len && cur != 0) {
				if (A[cur - 1] > 0)
					A[cur - 1] = -A[cur - 1];
				else if (A[cur - 1] == 0)
					A[cur - 1] = -len - 1;
			}
		}
		int i = 0;
		while (i < len && A[i] < 0)
			i++;
		return i + 1;
	}

	// Solution 2
	// Swap to correct position
	public int firstMissingPositive2(int[] nums) {
		int i = 0, n = nums.length;
		while (i < n) {
			// If the value is in the range of (0,length) and it's not at its correct position,
			// swap it to its correct position.
			// Else just continue;
			if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i])
				swap(nums, i, nums[i]);
			else
				i++;
		}
		int k = 1;
		// Check from k=1 to see whether each index and value can be corresponding.
		while (k < n && nums[k] == k)
			k++;

		// If it breaks because of empty array or reaching the end.
		// K must be the first missing number.
		if (n == 0 || k < n)
			return k;
		else // If k is hiding at position 0, K+1 is the number.
			return nums[0] == k ? k + 1 : k;

	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
