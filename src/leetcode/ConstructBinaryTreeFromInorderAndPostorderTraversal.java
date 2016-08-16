package leetcode;

import java.util.HashMap;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildRec(map, inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
	}

	private TreeNode buildRec(HashMap<Integer, Integer> map, int[] inorder, int is, int ie, int[] postorder, int ps,
	        int pe) {
		if (is > ie)
			return null;
		TreeNode root = new TreeNode(postorder[pe]);
		if (is == ie)
			return root;
		int i = map.get(postorder[pe]);
		// int leftLength = i - is;
		root.left = buildRec(map, inorder, is, i - 1, postorder, ps, ps + i - is - 1);
		root.right = buildRec(map, inorder, i + 1, ie, postorder, ps + i - is, pe - 1);
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
