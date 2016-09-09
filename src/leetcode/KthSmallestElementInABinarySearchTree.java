package leetcode;

import java.util.Stack;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up: What if the BST is modified (insert/delete operations) often and you need to find the
 * kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class KthSmallestElementInABinarySearchTree {
	public int kthSmallest(TreeNode root, int k) {
		int count = countNodes(root.left);
		if (k <= count) {
			return kthSmallest(root.left, k);
		} else if (k > count + 1) {
			return kthSmallest(root.right, k - 1 - count);
		}
		// k = count + 1
		return root.val;
	}

	public int countNodes(TreeNode n) {
		if (n == null)
			return 0;
		return 1 + countNodes(n.left) + countNodes(n.right);
	}

	public int kthSmallest2(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();

		while (root != null) {
			stack.push(root);
			root = root.left;
		}

		while (k != 0) {
			TreeNode n = stack.pop();
			k--;
			if (k == 0)
				return n.val;
			TreeNode right = n.right;
			while (right != null) {
				stack.push(right);
				right = right.left;
			}
		}

		return -1; // never hit if k is valid
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
