package leetcode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 */
public class BalancedBinaryTree {
	// top down solution
	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		while (root != null) {
			int left = maxHeight(root.left);
			int right = maxHeight(root.right);
			if (Math.abs(left - right) > 1)
				return false;
			else
				return isBalanced(root.left) && isBalanced(root.right);
		}
		return false;
	}

	public int maxHeight(TreeNode root) {
		if (root == null)
			return 0;
		int left = maxHeight(root.left) + 1;
		int right = maxHeight(root.right) + 1;
		return left > right ? left : right;
	}

	// bottom up solution (better)
	public boolean isBalanced2(TreeNode root) {
		return decide(root) != -1;
	}

	public int decide(TreeNode root) {
		if (root == null)
			return 0;
		int left = decide(root.left);
		int right = decide(root.right);
		if (left == -1 || right == -1 || Math.abs(left - right) > 1)
			return -1;
		return Math.max(left, right) + 1;
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
