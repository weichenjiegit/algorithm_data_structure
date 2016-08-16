package leetcode;
/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToBianrySearchTree {

	// Solution 1
	// Most straightforward solution is to convert list to an array.

	// Solution 2
	// Bottom up recursive
	public ListNode cur;

	public TreeNode sortedListToBST(ListNode head) {
		if (head == null)
			return null;
		int size = 1;
		ListNode ptr = head;
		this.cur = head;
		while (ptr.next != null) {
			ptr = ptr.next;
			size++;
		}
		return convert(0, size - 1);
	}

	public TreeNode convert(int start, int end) {
		if (start > end)
			return null;
		int mid = start + (end - start) / 2;
		TreeNode left = convert(start, mid - 1);
		TreeNode parent = new TreeNode(cur.val);
		parent.left = left;
		cur = cur.next;
		TreeNode right = convert(mid + 1, end);
		parent.right = right;
		return parent;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
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
