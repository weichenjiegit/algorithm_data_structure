package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the
 * nodes you can see ordered from top to bottom.
 * 
 * For example: Given the following binary tree,
 * 
 * <pre>
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * </pre>
 * 
 * You should return [1, 3, 4].
 */
public class BinaryTreeRightSideView {

	// This solution does work, because [1, 2, 3, 4] should return [1, 3, 4], i.e, the view can be
	// on the left sub-tree
	public List<Integer> rightSideViewInitialFlawedThought(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		TreeNode current = root;
		while (current != null) {
			result.add(current.val);
			if (current.right != null) {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		return result;
	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		rightView(root, result, 0);
		return result;
	}

	public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
		if (curr == null) {
			return;
		}
		if (currDepth == result.size()) {
			result.add(curr.val);
		}
		rightView(curr.right, result, currDepth + 1);
		rightView(curr.left, result, currDepth + 1);
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
