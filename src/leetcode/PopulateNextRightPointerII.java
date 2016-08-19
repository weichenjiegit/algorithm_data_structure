package leetcode;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * 
 * For example, Given the following binary tree,
 * 
 * 1 / \ 2 3 / \ \ 4 5 7
 * 
 * After calling your function, the tree should look like:
 * 
 * 1 -> NULL / \ 2 -> 3 -> NULL / \ \ 4-> 5 -> 7 -> NULL
 */
public class PopulateNextRightPointerII {
	// algorithm based on level order traversal
	public void connect(TreeLinkNode root) {
		TreeLinkNode nextHead = null; // head of the next level
		TreeLinkNode prev = null; // the leading node on the next level
		TreeLinkNode cur = root; // current node of current level
		while (cur != null) {
			while (cur != null) { // iterate on the current level
				// left child
				if (cur.left != null) {
					if (prev != null) {
						prev.next = cur.left;
					} else {
						nextHead = cur.left;
					}
					prev = cur.left;
				}
				// right child
				if (cur.right != null) {
					if (prev != null) {
						prev.next = cur.right;
					} else {
						nextHead = cur.right;
					}
					prev = cur.right;
				}
				// move to next node
				cur = cur.next;
			}
			// move to next level
			cur = nextHead;
			nextHead = null;
			prev = null;
		}
	}

	// Original solution. Too comlex coding styles.
	public void connect2(TreeLinkNode root) {
		TreeLinkNode cur_head = root;
		while (cur_head != null) {
			TreeLinkNode cur = cur_head;
			TreeLinkNode next_head = null;
			TreeLinkNode last_node = null;
			while (cur != null) {
				if (cur.left == null && cur.right == null)
					;
				else if (cur.left != null && cur.right != null) {
					if (last_node != null)
						last_node.next = cur.left;
					cur.left.next = cur.right;
					last_node = cur.right;
					if (next_head == null)
						next_head = cur.left;
				} else {
					TreeLinkNode child = null;
					if (cur.left != null)
						child = cur.left;
					else
						child = cur.right;
					if (last_node != null)
						last_node.next = child;
					last_node = child;
					if (next_head == null)
						next_head = child;
				}
				cur = cur.next;
			}
			if (last_node != null)
				last_node.next = null;
			cur_head = next_head;
		}
	}

	public static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.right = new TreeLinkNode(2);
		root.left.left = new TreeLinkNode(3);
		root.right.right = new TreeLinkNode(3);
		root.left.left.left = new TreeLinkNode(4);
		root.right.right.right = new TreeLinkNode(4);
		PopulateNextRightPointerII test = new PopulateNextRightPointerII();
		test.connect(root);
	}
}
