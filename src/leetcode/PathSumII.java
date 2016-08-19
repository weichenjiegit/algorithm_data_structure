package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given
 * sum.
 *
 */
public class PathSumII {

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		traversal(res, path, root, sum);
		return res;
	}

	public void traversal(List<List<Integer>> res, List<Integer> path, TreeNode root, int sum) {
		if (root == null)
			return;
		path.add(root.val);
		sum = sum - root.val;
		if (root.left == null && root.right == null && sum == 0)
			res.add(new ArrayList<>(path));
		traversal(res, path, root.left, sum);
		traversal(res, path, root.right, sum);
		path.remove(path.size() - 1);
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
