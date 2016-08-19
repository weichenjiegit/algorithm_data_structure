package leetcode;

/**
 *  Find the contiguous subarray within an array (containing at least one number)
 *  which has the largest sum.
 *  
 *  For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 *  the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 *  
 *  More practice:
 *  If you have figured out the O(n) solution,
 *  try coding another solution using the divide and conquer approach,
 *  which is more subtle.
 */
public class MaximumSubarray {
	// Solution 1: Kadane Algorithm O(n) Not working for all negative number
	// arrays
	public int maxSubArray(int[] A) {
		if (A.length == 0)
			return 0;
		int maxSum = A[0];
		int endSum = 0;
		for (int i = 0; i < A.length; i++) {
			if (endSum >= 0)
				endSum += A[i];
			else
				endSum = A[i];
			if (maxSum < endSum)
				maxSum = endSum;
		}
		return maxSum;
	}

	// Solution 2: Divide and Conquer O(nlogn)
	// T(n) = 2 * T(n/2) + n
	public int maxSubArray2(int[] A) {
		return find_max_subarray(A, 0, A.length - 1);
	}

	public int find_max_subarray(int[] A, int low, int high) {
		if (low == high)
			return A[low];
		else {
			int mid = (low + high) / 2;
			int left_sum = find_max_subarray(A, low, mid);
			int right_sum = find_max_subarray(A, mid + 1, high);
			int cross_sum = find_max_crossing_subarray(A, low, mid, high);
			if (left_sum >= right_sum && left_sum >= cross_sum)
				return left_sum;
			else if (right_sum >= left_sum && right_sum >= cross_sum)
				return right_sum;
			else
				return cross_sum;
		}
	}

	public int find_max_crossing_subarray(int[] A, int low, int mid, int high) {
		int left_sum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = mid; i >= low; i--) {
			sum += A[i];
			left_sum = Math.max(sum, left_sum);
		}
		int right_sum = Integer.MIN_VALUE;
		sum = 0;
		for (int i = mid + 1; i <= high; i++) {
			sum += A[i];
			right_sum = Math.max(sum, right_sum);
		}
		return left_sum + right_sum;
	}
}
