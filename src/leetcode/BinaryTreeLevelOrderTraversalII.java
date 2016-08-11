package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree,
 * return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * return its bottom-up level order traversal as:
 * [
 *  [15,7]
 *  [9,20],
 *  [3],
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
	// Solution 1
	// use a stack to store level order traversal result
	// then reverse the output order in the end
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<Integer> inner_list = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> outer_list = new ArrayList<ArrayList<Integer>>();

		if (root == null) {
			return outer_list;
		}

		Queue<TreeNode> current = new LinkedList<TreeNode>();
		Queue<TreeNode> next = new LinkedList<TreeNode>();
		Stack<ArrayList<Integer>> stack = new Stack<ArrayList<Integer>>();
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
				stack.push(inner_list);
				inner_list = new ArrayList<Integer>();
				// wrong using inner_list.clear()
				// clear() will clear the content in heap
				// elements in outer_list will also get cleaned up
				Queue<TreeNode> temp_queue = current;
				current = next;
				next = temp_queue;
			}
		}
		while (!stack.isEmpty()) { // Cannot use peek() on an empty Stack, it
									// would give out an exception. Instead, use
									// isEmpty().
			outer_list.add(stack.pop());
		}
		return outer_list;
	}

	// Solution 2. Using DFS level order traversal
	public ArrayList<ArrayList<Integer>> levelOrderBottom2(TreeNode root) {
		ArrayList<Integer> inner_list = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> outer_list = new ArrayList<ArrayList<Integer>>();
		int height = getHeight(root);
		while (height > 0) {
			levelTraversal(inner_list, root, height);
			outer_list.add(new ArrayList<Integer>(inner_list));
			inner_list.clear();
			height--;
		}
		return outer_list;
	}

	private void levelTraversal(ArrayList<Integer> inner_list, TreeNode node,
			int level) {
		if (node == null)
			return;
		if (level == 1)
			inner_list.add(node.val);
		else {
			levelTraversal(inner_list, node.left, level - 1);
			levelTraversal(inner_list, node.right, level - 1);
		}
	}

	private int getHeight(TreeNode root) {
		if (root == null)
			return 0;
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		return left > right ? left + 1 : right + 1;
	}

	/*
	 * Solution 2 time complexity analysis
	 * Answer:
	 * Although the DFS solution traverse the same node multiple times,
	 * it is not another order slower than the BFS solution.
	 * Here is the proof that the DFS solution above runs in O(N) time,
	 * where N is the number of nodes in the binary tree
	 * and we assume that the binary tree is balanced.
	 * 
	 * We first compute the complexity of printLevel for the kth level:

	 * T(k) = 2T(k-1) + c
	 * = 2k-1 T(1) + c
	 * = 2k-1 + c
	 * 
	 * Assuming it�s a balanced binary tree,
	 * then it would have a total of lg N levels.
	 * 
	 * Therefore, the complexity of printing all levels is:

	 * T(1) + T(2) + ... + T(lg N)
	 * = 1 + 2 + 22 + ... + 2lg N-1 + c
	 * = O(N)
	 * 
	 * Finding the maximum height of the tree also takes O(N) time,
	 * therefore the overall complexity is still O(N).
	 */
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
