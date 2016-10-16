package leetcode;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorInBST {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		while (root != null && root.val <= p.val)
			root = root.right;
		if (root == null)
			return null;
		TreeNode left = inorderSuccessor(root.left, p);
		return (left != null && left.val > p.val) ? left : root;
	}

	public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
		while (root != null && root.val <= p.val)
			root = root.left;
		if (root == null)
			return null;
		TreeNode right = inorderSuccessor(root.right, p);
		return (right != null && right.val > p.val) ? right : root;
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
