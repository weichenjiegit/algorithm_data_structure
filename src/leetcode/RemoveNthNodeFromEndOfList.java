package leetcode;

/**
 * Given a linked list,
 * remove the nth node from the end of list and return its head.
 * 
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end,
 * the linked list becomes 1->2->3->5.
 * 
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null)
			return null;
		int i = 0;
		ListNode tail = head;
		ListNode node = head;
		while (i++ < n)
			tail = tail.next;
		if (tail == null)
			return head.next;
		while (tail.next != null) {
			tail = tail.next;
			node = node.next;
		}
		node.next = node.next.next;
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
