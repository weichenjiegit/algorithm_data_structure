package leetcode;

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers
 * strictly alternate between positive and negative. The first difference (if one exists) may be
 * either positive or negative. A sequence with fewer than two elements is trivially a wiggle
 * sequence.
 * 
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are
 * alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle
 * sequences, the first because its first two differences are positive and the second because its
 * last difference is zero.
 * 
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle
 * sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero)
 * from the original sequence, leaving the remaining elements in their original order.
 */
public class WiggleSubsequence {
	/**
	 * 2,1,4,5,6,3,3,4,8,4
	 * 
	 * Step 1: First check requirements is to get small number. As 1<2 so the series will be 2,1
	 * 
	 * Step 2: Now we need big number that is greater than 1. As 4>1 so series will be 2,1,4
	 * 
	 * Step 3: Now we need small number. But 5>4 so 4 will be replaced by 5. So the series will
	 * become 2,1,5
	 * 
	 * Step 4: We need small number. But 6>5. Series will be 2,1,6
	 * 
	 * Step 5: Require small number. 3<6. Series will be 2,1,6,3
	 * 
	 * Step 6: Require big number. 3=3. No change in series 2,1,6,3
	 * 
	 * Step 7: Require big number. 4>3. Series will become 2,1,6,3,4
	 * 
	 * Step 8: Require small number. 8>4. 8 will replace 4 and series will become 2,1,6,3,8
	 * 
	 * Step 9: Require small number. 4<8. So final series will be 2,1,6,3,8,4
	 */
	public int wiggleMaxLength(int[] nums) {
		if (nums.length == 0 || nums.length == 1) {
			return nums.length;
		}
		int k = 0;
		// Skips all the same numbers from beginning eg 5, 5, 5, 1
		while (k < nums.length - 1 && nums[k] == nums[k + 1]) {
			k++;
		}
		if (k == nums.length - 1) {
			return 1;
		}
		int result = 2; // This will track the result of result array
		boolean smallReq = nums[k] < nums[k + 1]; // To check series starting pattern
		for (int i = k + 1; i < nums.length - 1; i++) {
			if (smallReq && nums[i + 1] < nums[i]) {
				nums[result] = nums[i + 1];
				result++;
				smallReq = !smallReq; // Toggle the requirement from small to big number
			} else {
				if (!smallReq && nums[i + 1] > nums[i]) {
					nums[result] = nums[i + 1];
					result++;
					smallReq = !smallReq; // Toggle the requirement from big to small number
				} else {
					nums[result] = nums[i + 1];
				}
			}
		}
		return result;
	}

	/**
	 * For every position in the array, there are only three possible statuses for it.
	 * 
	 * 1. up position, it means nums[i] > nums[i-1]
	 * 
	 * 2. down position, it means nums[i] < nums[i-1]
	 * 
	 * 3. equals to position, nums[i] == nums[i-1]
	 * 
	 * So we can use two arrays up[] and down[] to record the max wiggle sequence length so far at
	 * index i.
	 * 
	 * If nums[i] > nums[i-1], that means it wiggles up. the element before it must be a down
	 * position. so up[i] = down[i-1] + 1; down[i] keeps the same with before.
	 * 
	 * If nums[i] < nums[i-1], that means it wiggles down. the element before it must be a up
	 * position. so down[i] = up[i-1] + 1; up[i] keeps the same with before.
	 * 
	 * If nums[i] == nums[i-1], that means it will not change anything because it didn't wiggle at
	 * all. so both down[i] and up[i] keep the same.
	 * 
	 * In fact, we can reduce the space complexity to O(1), but current way is more easy to
	 * understanding.
	 */
	public int wiggleMaxLength2(int[] nums) {
		if (nums.length == 0)
			return 0;

		int[] up = new int[nums.length];
		int[] down = new int[nums.length];

		up[0] = 1;
		down[0] = 1;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				up[i] = down[i - 1] + 1;
				down[i] = down[i - 1];
			} else if (nums[i] < nums[i - 1]) {
				down[i] = up[i - 1] + 1;
				up[i] = up[i - 1];
			} else {
				down[i] = down[i - 1];
				up[i] = up[i - 1];
			}
		}

		return Math.max(down[nums.length - 1], up[nums.length - 1]);
	}

	// Space optimized O(1) DP.
	public int wiggleMaxLength3(int[] nums) {
		if (nums.length < 2)
			return nums.length;
		int down = 1, up = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1])
				up = down + 1;
			else if (nums[i] < nums[i - 1])
				down = up + 1;
		}
		return Math.max(down, up);
	}
}
