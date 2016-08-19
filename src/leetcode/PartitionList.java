package leetcode;

/**
 * Given a linked list and a value x, partition it such that
 * all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes
 * in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class PartitionList {
	public ListNode partition(ListNode head, int x) {
		if(head == null)
			return head;
		ListNode less = new ListNode(0);
		ListNode less_h = less;
		ListNode more = new ListNode(0);
		ListNode more_h = more;
		while(head != null){
			if(head.val < x){
				less.next = head;
				less = less.next;
				head = head.next;
			}
			else{
				more.next = head;
				more = more.next;
				head = head.next;
			}
		}
		more.next = null; // may still have other "less" nodes attached
		less.next = more_h.next;
		return less_h.next;
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
