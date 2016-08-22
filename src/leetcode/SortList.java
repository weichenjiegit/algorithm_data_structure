package leetcode;

/**
 * Definition for singly-linked list.
 */
public class SortList {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode mid = devideList(head);
		ListNode right = mid.next;
		mid.next = null;
		ListNode left = head;
		left = sortList(left);
		right = sortList(right);
		return mergeSort(left, right);
	}

	public ListNode mergeSort(ListNode left, ListNode right) {
		ListNode res = new ListNode(0);
		ListNode node = res;
		while (left != null && right != null) {
			if (left.val <= right.val) {
				node.next = left;
				left = left.next;
			} else {
				node.next = right;
				right = right.next;
			}
			node = node.next;
		}
		if (left != null)
			node.next = left;
		if (right != null)
			node.next = right;
		return res.next;
	}

	public ListNode devideList(ListNode head) {
		ListNode left = head;
		ListNode right = head;
		while (right.next != null) {
			right = right.next;
			if (right.next != null){
				left = left.next;
				right = right.next;
			}
		}
		return left;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public static void main(String[] args){
		ListNode head = new ListNode(6);
		head.next = new ListNode(5);
		head.next.next = new ListNode(4);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(2);
		head.next.next.next.next.next = new ListNode(1);
		SortList test = new SortList();
		test.sortList(head);
	}
}
