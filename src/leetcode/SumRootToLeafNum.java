package leetcode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a
 * number. An example is the root-to-leaf path 1->2->3 which represents the number 123. Find the
 * total sum of all root-to-leaf numbers.
 */
public class SumRootToLeafNum {
	public int SumNumbers(TreeNode root) {
		return sumNum(root, 0);
	}

	private int sumNum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		sum = sum * 10 + root.val;
		if (root.left == null && root.right == null) {
			return sum;
		}
		return sumNum(root.left, sum) + sumNum(root.right, sum);
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
