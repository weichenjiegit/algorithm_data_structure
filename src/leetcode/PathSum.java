package leetcode;

import java.util.Stack;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
 * all the values along the path equals the given sum.
 */
public class PathSum {

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root.left == null && root.right == null && sum - root.val == 0)
			return true;
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	public boolean hasPathSum2(TreeNode root, int sum) {
		Stack<TreeNode> s = new Stack<>();
		TreeNode pre = null, cur = root;
		int SUM = 0;
		while (cur != null || !s.empty()) {
			while (cur != null) {
				s.push(cur);
				SUM += cur.val;
				cur = cur.left;
			}
			cur = s.peek();
			if (cur.left == null && cur.right == null && SUM == sum) {
				return true;
			}
			if (cur.right != null && pre != cur.right) {
				cur = cur.right;
			} else {
				pre = cur;
				s.pop();
				SUM -= cur.val;
				cur = null;
			}
		}
		return false;
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
