package leetcode;

import java.util.Arrays;

/*
 *  Implement next permutation,
 *  which rearranges numbers into the lexicographically
 *  next greater permutation of numbers.
 *  
 *  If such arrangement is not possible,
 *  it must rearrange it as the lowest possible order
 *  (ie, sorted in ascending order).
 *  
 *  The replacement must be in-place, do not allocate extra memory.
 *  
 *  Here are some examples.
 *  Inputs are in the left-hand column
 *  and its corresponding outputs are in the right-hand column.
 *  
 *  1,2,3 → 1,3,2
 *  3,2,1 → 1,2,3
 *  1,1,5 → 1,5,1
 */
public class NextPermutation {
	// My original solution
	public void nextPermutation(int[] num) {
		int len = num.length;
		if (len <= 1)
			return;
		for (int i = len - 1; i > 0; i--) {
			// 找到第一个下降的元素
			if (num[i - 1] < num[i]) {
				// 找到被交换的那个数， min value in num[i...len - 1] that > num[i - 1]
				int j = i;
				while (j < len && num[j] > num[i - 1])
					j++;
				j--;
				int tmp = num[j];
				num[j] = num[i - 1];
				num[i - 1] = tmp;
				Arrays.sort(num, i, len);
				return;
			}
		}
		Arrays.sort(num);
		return;
	}

	// Textbook solution
	public void nextPermutationTextbook(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i + 1] <= nums[i]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
