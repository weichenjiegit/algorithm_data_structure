package leetcode;

/**
 * Given a list, rotate the list to the right by k places,
 * where k is non-negative.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
public class RotateList {
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null || n == 0)
			return head;
		ListNode node = head;
		int counter = 1;
		while (node.next != null) {
			node = node.next;
			counter++;
		}
		// node points to the last one
		n = n % counter;
		if (n == 0)
			return head;
		int step = counter - n;
		// connects the last one to head
		node.next = head;
		// make node equal to head again
		node = head;
		while (step > 1) {
			step--;
			node = node.next;
		}
		head = node.next;
		node.next = null;
		return head;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
