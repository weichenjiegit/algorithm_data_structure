package leetcode;

/**
 *  Given a binary tree, find the maximum path sum.
 *  The path may start and end at any node in the tree.
 *  
 *  For example:
 *  Given the below binary tree,
 *      1
 *     / \
 *    2   3
 * Return 6.
 */
public class BinaryTreeMaximumPathSum {
	private int max;

	public int maxPathSum(TreeNode root) {
		max = root.val;
		curPathMax(root);
		return max;
	}

	public int curPathMax(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l = curPathMax(root.left);
		int r = curPathMax(root.right);
		int m = root.val;
		if (l > 0)
			m += l;
		if (r > 0)
			m += r;
		max = Math.max(max, m);

		return Math.max(l, r) > 0 ? Math.max(l, r) + root.val : root.val;
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
