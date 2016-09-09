package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Invert a binary tree.
 * 
 * <pre>
 * 
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 
 * to
 * 
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * </pre>
 */
public class InvertBinaryTree {

	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			TreeNode tmp = node.left;
			node.left = node.right;
			node.right = tmp;
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
		return root;
	}

	public TreeNode invertTree2(TreeNode root) {
		if (root == null)
			return root;
		if (root.left == null && root.right == null)
			return root;
		TreeNode right = invertTree(root.right);
		TreeNode left = invertTree(root.left);
		root.left = right;
		root.right = left;
		return root;
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
