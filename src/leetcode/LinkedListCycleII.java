package leetcode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * Note: Do not modify the linked list.
 *
 */
public class LinkedListCycleII {

	public ListNode detectCycle(ListNode head) {
		if (head == null)
			return head;
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				break;
		}
		if (fast == null || fast.next == null)
			return null;
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
