package leetcode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 <= m <= n <= length of list.
 */
public class ReverseLinkedListII {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head == null)
			return head;
		int dis = n - m;
		if(dis == 0)
			return head;
		ListNode res = new ListNode(0);
		res.next = head;
		ListNode pre = res;
		ListNode cur = head;
		while(m > 1){
			pre = cur;
			cur = cur.next;
			m--;
		}
		ListNode next = cur.next;
		while(next != null && dis > 0){
			cur.next = next.next;
			next.next = pre.next;
			pre.next = next;
			dis--;
			next = cur.next;
		}
		return res.next;
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
		ReverseLinkedListII.ListNode head = new ReverseLinkedListII.ListNode(1);
		head.next = new ReverseLinkedListII.ListNode(2);
		head.next.next = new ReverseLinkedListII.ListNode(3);
		ReverseLinkedListII test = new ReverseLinkedListII();
		test.reverseBetween(head, 1, 3);
	}
}
