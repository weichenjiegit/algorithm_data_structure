package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array
 * has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * Given nums = [5, 2, 6, 1]
 * 
 * <pre>
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * </pre>
 * 
 * Return the array [2, 1, 1, 0].
 */
public class CountOfSmallerNumbersAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;
		TreeNode root = new TreeNode(nums[nums.length - 1]);
		res.add(0);
		for (int i = nums.length - 2; i >= 0; i--) {
			int count = insertNode(root, nums[i]);
			res.add(count);
		}
		Collections.reverse(res);
		return res;
	}

	private int insertNode(TreeNode root, int val) {
		int thisCount = 0;
		while (root != null) {
			if (val <= root.val) {
				root.count++;
				if (root.left == null) {
					root.left = new TreeNode(val);
					break;
				} else {
					root = root.left;
				}
			} else {
				thisCount += root.count;
				if (root.right == null) {
					root.right = new TreeNode(val);
					break;
				} else {
					root = root.right;
				}
			}
		}
		return thisCount;
	}

	public class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;
		int count = 1;

		public TreeNode(int val) {
			this.val = val;
		}
	}
}
