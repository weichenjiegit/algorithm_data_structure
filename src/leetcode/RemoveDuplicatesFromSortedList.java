package leetcode;

/**
 * Given a sorted linked list,
 * delete all duplicates such that each element appear onlyonce.
 * 
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class RemoveDuplicatesFromSortedList {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public class Solution {
		public ListNode deleteDuplicates(ListNode head) {
			if (head == null)
				return head;
			ListNode cur = head;
			ListNode next;
			while (cur != null && cur.next != null) {
				next = cur.next;
				if (next.val == cur.val) {
					cur.next = next.next;
				} else {
					cur = cur.next;
				}
			}
			return head;
		}
	}
}
