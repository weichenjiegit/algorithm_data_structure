package leetcode;

import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place. For example, Given
 * 
 * 1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like: 1 \ 2 \ 3 \ 4 \ 5 \ 6 Hints: If you
 * notice carefully in the flattened tree, each node's right child points to the next node of a
 * pre-order traversal.
 */
public class FlattenBinaryTreeLinkedList {
	// Solution 1
	// Recursive
	public void flatten(TreeNode root) {
		if (root == null)
			return;

		TreeNode left = root.left;
		TreeNode right = root.right;

		root.left = null;

		flatten(left);
		flatten(right);

		root.right = left;
		TreeNode cur = root;
		while (cur.right != null)
			cur = cur.right;
		cur.right = right;
	}

	// True in place
	public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        while (root != null) {
            if (root.left == null) {
                root = root.right;
                continue;
            }
            TreeNode left = root.left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }

	// Solution 3
	// Use a stack
	public void flatten3(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode node = null;
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			if (node != null) {
				node.left = null;
				node.right = cur;
			}
			node = cur;
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}

	// similar
	public void flatten4(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stk = new Stack<TreeNode>();
		stk.push(root);
		while (!stk.isEmpty()) {
			TreeNode curr = stk.pop();
			if (curr.right != null)
				stk.push(curr.right);
			if (curr.left != null)
				stk.push(curr.left);
			if (!stk.isEmpty())
				curr.right = stk.peek();
			curr.left = null;
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
