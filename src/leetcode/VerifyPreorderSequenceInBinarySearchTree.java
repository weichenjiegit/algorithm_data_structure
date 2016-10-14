package leetcode;

import java.util.Stack;

/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a
 * binary search tree.
 * 
 * You may assume each number in the sequence is unique.
 * 
 * Follow up: Could you do it using only constant space complexity?
 */
public class VerifyPreorderSequenceInBinarySearchTree {
	public boolean verifyPreorder(int[] preorder) {
		int low = Integer.MIN_VALUE;
		Stack<Integer> path = new Stack<>();
		for (int p : preorder) {
			if (p < low)
				return false;
			while (!path.empty() && p > path.peek())
				low = path.pop();
			path.push(p);
		}
		return true;
	}

	// Constant space.
	public boolean verifyPreorder2(int[] preorder) {
		int low = Integer.MIN_VALUE, i = -1;
		for (int p : preorder) {
			if (p < low)
				return false;
			while (i >= 0 && p > preorder[i])
				low = preorder[i--];
			preorder[++i] = p;
		}
		return true;
	}
}
