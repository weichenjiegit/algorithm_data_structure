package leetcode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the
 * root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the
 * height of the tree.
 */
public class BinarySearchTreeIterator {

	private Stack<TreeNode> stack = new Stack<>();

	public BinarySearchTreeIterator(TreeNode root) {
		pushAll(root);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode temp = stack.pop();
		pushAll(temp.right);
		return temp.val;
	}

	private void pushAll(TreeNode node) {
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
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
