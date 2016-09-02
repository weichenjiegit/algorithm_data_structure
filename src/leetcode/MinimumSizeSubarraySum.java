package leetcode;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a
 * subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length
 * under the problem constraint.
 *
 */
public class MinimumSizeSubarraySum {
	// O(n)
	public int minSubArrayLen(int s, int[] nums) {
		int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
		while (end < nums.length) {
			while (end < nums.length && sum < s) {
				sum += nums[end];
				end++;
			}
			if (sum < s)
				break;
			while (start < end && sum >= s) {
				sum -= nums[start];
				start++;
			}
			if (end - start + 1 < minLen)
				minLen = end - start + 1;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	// O(n log n)
	public int minSubArrayLen3(int s, int[] nums) {
		int[] sums = new int[nums.length + 1];
		for (int i = 1; i < sums.length; i++)
			sums[i] = sums[i - 1] + nums[i - 1];
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < sums.length; i++) {
			int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
			if (end == sums.length)
				break;
			if (end - i < minLen)
				minLen = end - i;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	private int binarySearch(int lo, int hi, int key, int[] sums) {
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (sums[mid] >= key) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	public static void main(String[] args) {
		new MinimumSizeSubarraySum().minSubArrayLen(7, new int[] { 2, 3, 1, 2 });
	}
}
