package leetcode;

/**
 * Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {

	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null)
			return 0;
		int ans = 0;
		if (root.left != null) {
			if (root.left.left == null && root.left.right == null)
				ans += root.left.val;
			else
				ans += sumOfLeftLeaves(root.left);
		}
		if (root.right != null) {
			ans += sumOfLeftLeaves(root.right);
		}

		return ans;
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
