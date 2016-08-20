package leetcode;

/**
 * Given a binary tree
 * 
 * struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; }
 * 
 * Populate each next pointer to point to its next right node. If there is no next right node, the
 * next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space. You may assume that it is a perfect binary tree (ie, all
 * leaves are at the same level, and every parent has two children).
 * 
 * For example, Given the following perfect binary tree,
 * 
 * <pre>
 *      1
 *     /  \
 *    2    3
 *   / \  / \
 *  4  5  6  7
 * 
 *  After calling your function, the tree should look like:
 * 
 *      1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \  / \
 *  4->5->6->7 -> NULL
 * </pre>
 * 
 * Require constant space, so no level traversal is allowed.
 */
public class PopulateNextRightPointer {
	public void connect(TreeLinkNode root) {
		while (root != null) {
			TreeLinkNode pre = root;
			TreeLinkNode sib;
			while (pre != null) {
				if (pre.left != null)
					pre.left.next = pre.right;
				sib = pre.next;
				if (sib != null && pre.right != null)
					pre.right.next = sib.left;
				pre = sib;
			}
			root = root.left;
		}
	}

	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
}
