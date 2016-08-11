package leetcode;

/**
 * Given a binary tree,
 * return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * return its level order traversal as:
 * [
 *  [3],
 *  [9,20],
 *  [15,7]
 * ]
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<Integer> inner_list = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> outer_list = new ArrayList<ArrayList<Integer>>();

		if (root == null) {
			return outer_list;
		}

		Queue<TreeNode> current = new LinkedList<TreeNode>();
		Queue<TreeNode> next = new LinkedList<TreeNode>();
		TreeNode temp;
		current.offer(root);
		while (!current.isEmpty()) {
			temp = current.poll();
			inner_list.add(temp.val);
			if (temp.left != null) {
				next.offer(temp.left);
			}
			if (temp.right != null) {
				next.offer(temp.right);
			}
			if (current.isEmpty()) {
				outer_list.add(inner_list);
				inner_list = new ArrayList<Integer>();
				// wrong using inner_list.clear()
				// clear() will clear the content in heap
				// elements in outer_list will also get cleaned up
				Queue<TreeNode> temp_queue = current;
				current = next;
				next = temp_queue;
			}
		}
		return outer_list;
	}

	// use two index to track positions within one queue
	// "first" is used to measure if there is any elements left in current level
	// "second" is used to measure how many elements in current level
	public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
		ArrayList<Integer> inner_list = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> outer_list = new ArrayList<ArrayList<Integer>>();

		if (root == null) {
			return outer_list;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode temp;
		queue.offer(root);
		int first = 1;
		int second = 0;
		while (!queue.isEmpty()) {
			temp = queue.poll();
			first--;
			inner_list.add(temp.val);
			if (temp.left != null) {
				queue.offer(temp.left);
				second++;
			}
			if (temp.right != null) {
				queue.offer(temp.right);
				second++;
			}
			if (first == 0) {
				first = second;
				second = 0;
				outer_list.add(inner_list);
				inner_list = new ArrayList<Integer>();
			}
		}
		return outer_list;
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
