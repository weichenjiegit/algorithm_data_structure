package leetcode;

import java.util.HashMap;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildRec(map, preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private TreeNode buildRec(HashMap<Integer, Integer> map, int[] preorder, int ps, int pe, int[] inorder, int is,
	        int ie) {
		if (ps > pe)
			return null;
		TreeNode root = new TreeNode(preorder[ps]);
		if (ps == pe)
			return root;
		int i = map.get(preorder[ps]); // divide point. left is left subtree.
		int leftLen = i - is;
		root.left = buildRec(map, preorder, ps + 1, ps + leftLen, inorder, is, i - 1);
		root.right = buildRec(map, preorder, ps + i - is + 1, pe, inorder, i + 1, ie);
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
