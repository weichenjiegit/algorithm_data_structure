package leetcode;

/**
 * Given a complete binary tree, count the number of nodes.
 * 
 * In a complete binary tree every level, except possibly the last, is completely filled, and all
 * nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive
 * at the last level h.
 */
public class CountCompleteTreeNodes {

	/**
	 * check whether the height of the right subtree is just one less than that of the whole tree,
	 * meaning left and right subtree have the same height.
	 * 
	 * If yes, then the last node on the last tree row is in the right subtree and the left subtree
	 * is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root
	 * node plus recursively the number of nodes in the right subtree.
	 * 
	 * If no, then the last node on the last tree row is in the left subtree and the right subtree
	 * is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1
	 * root node plus recursively the number of nodes in the left subtree.
	 */
	public int countNodes(TreeNode root) {
		int h = height(root);
		return h < 0 ? 0
		        : height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
		                : (1 << h - 1) + countNodes(root.left);
	}

	int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left);
	}

	// Same solution. Only iterative.
	public int countNodes2(TreeNode root) {
		int nodes = 0, h = height(root);
		while (root != null) {
			if (height(root.right) == h - 1) {
				nodes += 1 << h;
				root = root.right;
			} else {
				nodes += 1 << h - 1;
				root = root.left;
			}
			h--;
		}
		return nodes;
	}

	public int countNodes3(TreeNode root) {
		if (root == null)
			return 0;
		TreeNode left = root, right = root;
		int height = 0;
		while (right != null) {
			left = left.left;
			right = right.right;
			height++;
		}
		if (left == null) // right == null && left == null, full binary tree.
			return (1 << height) - 1;
		return 1 + countNodes3(root.left) + countNodes3(root.right);
	}

	public int countNodes4(TreeNode root) {
		if (root == null)
			return 0;
		return 1 + countNodes4(root.left) + countNodes4(root.right);
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
