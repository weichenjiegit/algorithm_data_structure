package leetcode;

/**
 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form
 * f(x) = ax ^ 2 + bx + c to each element x in the array.
 * 
 * The returned array must be in sorted order.
 * 
 * Expected time complexity: O(n)
 */
public class SortTransformedArray {
	/**
	 * For a parabola,
	 * 
	 * if a >= 0, the min value is at its vertex. So our our sorting should goes from two end points
	 * towards the vertex, high to low.
	 * 
	 * if a < 0, the max value is at its vertex. So our sort goes the opposite way.
	 */
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int n = nums.length;
		int[] sorted = new int[n];
		int i = 0, j = n - 1;
		int index = a >= 0 ? n - 1 : 0;
		while (i <= j) {
			if (a >= 0) {
				sorted[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c)
				        : quad(nums[j--], a, b, c);
			} else {
				sorted[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[j--], a, b, c)
				        : quad(nums[i++], a, b, c);
			}
		}
		return sorted;
	}

	private int quad(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}

	public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
		int[] res = new int[nums.length];
		int start = 0;
		int end = nums.length - 1;
		int i = a >= 0 ? nums.length - 1 : 0;
		while (start <= end) {
			int startNum = getNum(nums[start], a, b, c);
			int endNum = getNum(nums[end], a, b, c);
			if (a >= 0) {
				if (startNum >= endNum) {
					res[i--] = startNum;
					start++;
				} else {
					res[i--] = endNum;
					end--;
				}
			} else {
				if (startNum <= endNum) {
					res[i++] = startNum;
					start++;
				} else {
					res[i++] = endNum;
					end--;
				}
			}
		}
		return res;
	}

	private int getNum(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}
}
