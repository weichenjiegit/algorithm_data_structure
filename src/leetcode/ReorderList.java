package leetcode;

/**
 *  Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 *  reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *  
 *  You must do this in-place without altering the nodes' values.
 *  
 *  For example,
 *  Given {1,2,3,4}, reorder it to {1,4,2,3}. 
 */
public class ReorderList {

	public void reorderList(ListNode head) {
		if (head == null)
			return;
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode temp = slow.next;
		slow.next = null;
		slow = temp;
		ListNode head2 = new ListNode(0);
		while (slow != null) {
			temp = slow;
			slow = slow.next;
			temp.next = head2.next;
			head2.next = temp;
		}
		ListNode pointer1 = head;
		ListNode pointer2 = head2.next;
		while (pointer1 != null && pointer2 != null) {
			temp = pointer2.next;
			pointer2.next = pointer1.next;
			pointer1.next = pointer2;
			pointer1 = pointer1.next.next;
			pointer2 = temp;
		}
		if(pointer1.next != null)
			pointer1.next = null;
		return;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public static void main(String[] args){
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		ReorderList test = new ReorderList();
		test.reorderList(head);
	}
}
