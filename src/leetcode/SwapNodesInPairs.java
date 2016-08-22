package leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space.
 * You may not modify the values in the list, only nodes itself can be changed.
 */
public class SwapNodesInPairs {
	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return head;
		if (head.next == null)
			return head;
		ListNode odd = head;
		ListNode even = head.next;
		ListNode pre = head;
		odd.next = even.next;
		even.next = odd;
		head = even;
		pre = odd;
		while (odd.next != null && odd.next.next != null) {
			odd = odd.next;
			even = odd.next;
			odd.next = even.next;
			even.next = odd;
			pre.next = even;
			pre = odd;
		}
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
