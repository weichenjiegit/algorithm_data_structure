package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left
 * to right, then right to left for the next level and alternate between).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7},
 * 
 * 3 / \ 9 20 / \ 15 7
 * 
 * return its zigzag level order traversal as:
 * 
 * [ [3], [20,9], [15,7] ]
 */

public class BinaryTreeZigzagLevelOrderTraversal {
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> line;
		Stack<TreeNode> level = new Stack<TreeNode>();
		Stack<TreeNode> next_lev;
		if (root == null)
			return res;
		level.add(root);
		boolean flag = false;
		while (!level.isEmpty()) {
			line = new ArrayList<Integer>();
			next_lev = new Stack<TreeNode>();
			while (!level.isEmpty()) {
				TreeNode cur = level.pop();
				line.add(cur.val);
				if (flag) {
					if (cur.right != null)
						next_lev.add(cur.right);
					if (cur.left != null)
						next_lev.add(cur.left);
				} else {
					if (cur.left != null)
						next_lev.add(cur.left);
					if (cur.right != null)
						next_lev.add(cur.right);
				}
			}
			res.add(line);
			level = next_lev;
			flag = !flag;
		}
		return res;
	}

	public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
		List<List<Integer>> rlts = new ArrayList<List<Integer>>();
		if (root == null) {
			return rlts;
		}
		Queue<TreeNode> que = new LinkedList<TreeNode>();
		que.add(root);
		boolean isForward = true;
		int lvlNumNodes = 1;
		LinkedList<Integer> rlt = new LinkedList<Integer>();
		while (!que.isEmpty()) {
			TreeNode node = que.poll();
			// From left to right
			if (isForward) {
				rlt.add(node.val);
			} else {
				// From right to left
				rlt.addFirst(node.val);
			}
			if (node.left != null) {
				que.add(node.left);
			}
			if (node.right != null) {
				que.add(node.right);
			}
			--lvlNumNodes;
			// New level
			if (lvlNumNodes == 0) {
				rlts.add(rlt);
				lvlNumNodes = que.size();
				if (lvlNumNodes != 0) {
					rlt = new LinkedList<Integer>();
				}
				// Change direction
				isForward = !isForward;
			}
		}
		return rlts;
	}

	public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
		List<List<Integer>> sol = new ArrayList<>();
		travel(root, sol, 0);
		return sol;
	}

	private void travel(TreeNode curr, List<List<Integer>> sol, int level) {
		if (curr == null)
			return;
		if (sol.size() <= level) {
			List<Integer> newLevel = new LinkedList<>();
			sol.add(newLevel);
		}
		List<Integer> collection = sol.get(level);
		if (level % 2 == 0) {
			collection.add(curr.val);
		} else {
			collection.add(0, curr.val);
		}
		travel(curr.left, sol, level + 1);
		travel(curr.right, sol, level + 1);
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		BinaryTreeZigzagLevelOrderTraversal test = new BinaryTreeZigzagLevelOrderTraversal();
		test.zigzagLevelOrder(root);
	}
}
