package leetcode;

public class MinimumDepthOfBinaryTree {

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		int minLeftDepth = minDepth(root.left);
		int minRightDepth = minDepth(root.right);
		int minDepth = Math.min(minLeftDepth, minRightDepth);
		return 1 + (minDepth > 0 ? minDepth : Math.max(minLeftDepth, minRightDepth));
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
