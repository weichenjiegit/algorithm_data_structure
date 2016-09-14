package leetcode;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j),
 * inclusive.
 * 
 * Example:
 * 
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * sumRange(0, 2) -> 1
 * 
 * sumRange(2, 5) -> -1
 * 
 * sumRange(0, 5) -> -3
 * 
 * Note:
 * 
 * You may assume that the array does not change. There are many calls to sumRange function.
 *
 */
public class RangeSumQueryImmutable {
	public class NumArray {
		private int[] presumCache;

		public NumArray(int[] nums) {
			this.presumCache = nums;
			for (int i = 1; i < presumCache.length; i++)
				presumCache[i] += presumCache[i - 1];
		}

		public int sumRange(int i, int j) {
			if (i == 0)
				return presumCache[j];

			return presumCache[j] - presumCache[i - 1];
		}
	}
}
