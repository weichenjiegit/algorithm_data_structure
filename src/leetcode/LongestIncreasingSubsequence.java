package leetcode;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3, 7,
 * 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only
 * necessary for you to return the length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
	// Solution DP. O(n ^ 2)
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int[] dp = new int[nums.length];
		for (int i = 0; i < dp.length; i++)
			dp[i] = 1;

		for (int i = 1; i < nums.length; i++)
			for (int j = i - 1; j >= 0; j--)
				if (nums[i] > nums[j] && dp[i] < dp[j] + 1)
					dp[i] = dp[j] + 1;

		int max = dp[0];
		for (int i = 1; i < dp.length; i++)
			if (dp[i] > max)
				max = dp[i];

		return max;
	}

	// Binary search solution. O(n log n)
	/**
	 * I use an array dp[] to store the largest elements in the increasing subsequences and the
	 * array's index is the length of the subsequence (since this, we can make sure that the array
	 * dp[] is in-order). The idea is keep checking if nums[i] is bigger than dp[len] or not:
	 * nums[i] > dp[len] means we can retrieve a longer subsequence by adding the current element
	 * nums[i] into the result, so we just increase the pointer 'len' by one and put the current
	 * element into the new index; otherwise we need to do a binary search to find out the index of
	 * the largest element st. dp[index] < nums[i] && dp[index + 1] > nums[i] and update the
	 * dp[index] with value nums[i].
	 * 
	 * Let's see an example: intput: 2 4 9 3 7 8
	 * 
	 * The array 'dp' looks like these in first two iterations:
	 * 
	 * 2 4 0 0 0 (len = 1)
	 * 
	 * 2 4 9 0 0 (len = 2)
	 * 
	 * In the third iteration, we notice that dp[len] ('9' here) > nums[i] ('3' here), so we update
	 * the dp[index] with value '3', then we got:
	 * 
	 * 2 3 9 0 0 (len = 2) <---- Attention here, this array doesn't mean we can retrieve subsequence
	 * [2,3,9], it only means the largest element in a length 1 subsequence is '2' ([2]), the
	 * largest element in a length 2 subsequence is '3' ([2,3]) and the largest element in a length
	 * 3 subsequence is '9' ([2,4,9]) by far.
	 * 
	 * In next iteration, we update the dp[index] with value '7' again and we got:
	 * 
	 * 2 3 7 0 0 (len = 2)
	 * 
	 * In the final round, obviously dp[len] (7) < nums[i] (8) so we increase the 'len' by one and
	 * put '8' into dp[3] then we got: 2 3 7 8 0 (len = 3)
	 * 
	 * Clearly len + 1 = 4 is our result : )
	 * 
	 * PS: Keep in mind that 'len' always indicate the length of the longest increasing subsequence
	 * in each iteration.
	 */
	public int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int len = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > dp[len]) {
				dp[++len] = nums[i];
			} else {
				int index = search(dp, len, nums[i]);
				dp[index] = nums[i];
			}
		}
		return len + 1;
	}

	private int search(int[] dp, int len, int val) {
		int start = 0;
		while (start <= len) {
			int mid = start + (len - start) / 2;
			if (dp[mid] == val) {
				return mid;
			} else if (dp[mid] < val) {
				start = mid + 1;
			} else {
				len = mid - 1;
			}
		}
		return start;
	}
}
