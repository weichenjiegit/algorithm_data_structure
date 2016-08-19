package leetcode;

/**
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 */
public class MaximumDepthBinaryTree {
	public int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		else
			return traversal(root, 1);
	}
	
	public int traversal(TreeNode root, int depth){
		if(root == null)
			return Math.max(0, depth);
		int left = depth; int right = depth;
		if(root.left != null)
			left = traversal(root.left, left + 1);
		if(root.right != null)
			right = traversal(root.right, right + 1);
		return Math.max(left, right);
	}

	// Bottom up
	public int maxDepth2(TreeNode root) {
		if(root == null)
			return 0;
		int left = maxDepth2(root.left) + 1;
		int right = maxDepth2(root.right) + 1;
		return Math.max(left, right);
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args){
		MaximumDepthBinaryTree.TreeNode root = new MaximumDepthBinaryTree.TreeNode(1);
		root.left = new MaximumDepthBinaryTree.TreeNode(2);
		MaximumDepthBinaryTree test = new MaximumDepthBinaryTree();
		System.out.println(test.maxDepth(root));
	}
}
