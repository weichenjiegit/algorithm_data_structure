package leetcode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is
 * closest to the target.
 * 
 * Note:
 * 
 * Given target value is a floating point.
 * 
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */
public class ClosestBinarySearchTreeValue {
	public int closestValue(TreeNode root, double target) {
		int ret = root.val;
		while (root != null) {
			if (Math.abs(target - root.val) < Math.abs(target - ret)) {
				ret = root.val;
			}
			root = root.val > target ? root.left : root.right;
		}
		return ret;
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
