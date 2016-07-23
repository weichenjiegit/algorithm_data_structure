package leetcode;

public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		ListNode result = new ListNode(0);
		ListNode current = result;
		int carry = 0;
		int value = 0;
		while (l1 != null && l2 != null) {
			value = l1.val + l2.val + carry;
			current.next = new ListNode(value % 10);
			current = current.next;
			carry = value / 10;
			l1 = l1.next;
			l2 = l2.next;
		}
		while (l1 != null) {
			value = l1.val + carry;
			current.next = new ListNode(value % 10);
			current = current.next;
			carry = value / 10;
			l1 = l1.next;
		}
		while (l2 != null) {
			value = l2.val + carry;
			current.next = new ListNode(value % 10);
			current = current.next;
			carry = value / 10;
			l2 = l2.next;
		}
		if (carry != 0)
			current.next = new ListNode(1);

		return result.next;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
