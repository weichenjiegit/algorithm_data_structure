package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all
 * leaves, repeat until the tree is empty.
 */
public class FindLeavesOfBinaryTree {
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		height(root, res);
		return res;
	}

	private int height(TreeNode node, List<List<Integer>> res) {
		if (node == null)
			return -1;
		int level = 1 + Math.max(height(node.left, res), height(node.right, res));
		if (res.size() < level + 1)
			res.add(new ArrayList<>());
		res.get(level).add(node.val);
		return level;
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
