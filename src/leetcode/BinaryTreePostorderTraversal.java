package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class BinaryTreePostorderTraversal {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		TreeNode pre = null;
		TreeNode cur = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			cur = stack.peek();
			if (pre == null || (pre != null && (pre.left == cur || pre.right == cur))) {
				if (cur.left != null)
					stack.push(cur.left);
				else if (cur.right != null)
					stack.push(cur.right);
			} else if (cur.left == pre) {
				if (cur.right != null)
					stack.push(cur.right);
			} else {
				res.add(stack.pop().val);
			}
			pre = cur;
		}
		return res;
	}

	public ArrayList<Integer> postorderTraversal2(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		TreeNode node = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			node = stack.pop();
			res.add(node.val);
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		Collections.reverse(res);
		return res;
	}
}
