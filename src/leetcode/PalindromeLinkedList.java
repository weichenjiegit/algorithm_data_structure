package leetcode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {

	public boolean isPalindrome(ListNode head) {
		if (head == null) {
			return true;
		}
		ListNode preHead = new ListNode(-1);
		ListNode fast = head;
		ListNode slow = head;
		ListNode temp;
		// find mid pointer, and reverse head half part
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			temp = slow;
			slow = slow.next;
			temp.next = preHead.next;
			preHead.next = temp;
		}

		// odd number of elements, need to move slow one step
		if (fast.next == null) {
			slow = slow.next;
		} else { // even number of elements, need to add current slow to the reverse list and move
		         // slow one more step
			temp = slow;
			slow = slow.next;
			temp.next = preHead.next;
			preHead.next = temp;
		}
		// compare from mid to head/tail
		temp = preHead.next;
		while (slow != null) {
			if (temp.val != slow.val) {
				return false;
			}
			temp = temp.next;
			slow = slow.next;
		}
		return true;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
