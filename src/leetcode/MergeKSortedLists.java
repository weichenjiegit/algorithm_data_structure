package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its
 * complexity.
 */
public class MergeKSortedLists {

	/**
	 * Solution 1. Using min heap. O(n*logK)，Get node from heap is O(logK)。
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;
		Comparator<ListNode> comparator = new Comparator<ListNode>() {
			public int compare(ListNode m, ListNode n) {
				return Integer.compare(m.val, n.val);
			}
		};
		PriorityQueue<ListNode> q = new PriorityQueue<>(lists.size(), comparator);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null)
				q.add(lists.get(i));
		}
		ListNode head = null, cur = null;
		while (!q.isEmpty()) {
			if (head == null) {
				head = q.poll();
				cur = head;
			} else {
				cur.next = q.poll();
				cur = cur.next;
			}
			if (cur.next != null)
				q.add(cur.next);
		}
		return head;
	}

	/**
	 * Divide and conquer
	 * n is the average length of the lists. T(k) = 2T(k/2) + O(nk) = O(nklogk)
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		return divideAndConquer(lists, 0, lists.length - 1);
	}

	private ListNode divideAndConquer(ListNode[] lists, int l, int r) {
		if (r < l)
			return null;
		if (r == l)
			return lists[r];

		int mid = (l + r) / 2;
		ListNode a = divideAndConquer(lists, l, mid), b = divideAndConquer(lists, mid + 1, r);
		ListNode dmHead = new ListNode(0), cur = dmHead;
		while (a != null && b != null) {
			if (a.val < b.val) {
				cur.next = a;
				a = a.next;
			} else {
				cur.next = b;
				b = b.next;
			}
			cur = cur.next;
		}
		cur.next = (a != null) ? a : b;

		return dmHead.next;
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
