package leetcode;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest
 * means subtree with largest number of nodes in it.
 */
public class LargestBSTSubtree {

	class Result {
		// (size, rangeLower, rangeUpper)
		// -- size of current tree, range of current tree
		// [rangeLower, rangeUpper]
		int size;
		int lower;
		int upper;

		Result(int size, int lower, int upper) {
			this.size = size;
			this.lower = lower;
			this.upper = upper;
		}
	}

	private int max = 0;

	public int largestBSTSubtree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		traverse(root);
		return max;
	}

	private Result traverse(TreeNode root) {
		if (root == null) {
			return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		Result left = traverse(root.left);
		Result right = traverse(root.right);
		if (left.size == -1 || right.size == -1 || root.val < left.upper || root.val > right.lower) {
			return new Result(-1, 0, 0);
		}
		int size = left.size + 1 + right.size;
		max = Math.max(size, max);
		return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
