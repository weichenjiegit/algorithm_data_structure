package leetcode;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must
 * have the same probability of being chosen.
 * 
 * Follow up: What if the linked list is extremely large and its length is unknown to you? Could you
 * solve this efficiently without using extra space?
 */
public class LinkedListRandomNode {

	public class Solution {

		ListNode head;

		/**
		 * @param head
		 *            The linked list's head. Note that the head is guaranteed to be not null, so it
		 *            contains at least one node.
		 */
		public Solution(ListNode head) {
			this.head = head;
		}

		/** Returns a random node's value. */
		public int getRandom() {
			ListNode c = head;
			int r = c.val;
			for (int i = 1; c.next != null; i++) {
				c = c.next;
				if (randInt(0, i) == i)
					r = c.val;
			}
			return r;
		}

		private int randInt(int min, int max) {
			return min + (int) (Math.random() * ((max - min) + 1));
		}
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
