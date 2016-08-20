package leetcode;

/**
 * Given a linked list,
 * reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k
 * then left-out nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k == 1)
			return head;
		ListNode cur = head;
		int counter = 1;
		while (counter < k && cur.next != null) {
			cur = cur.next;
			counter++;
		}
		if (counter < k)
			return head;
		cur = head;
		ListNode next = head.next;
		ListNode temp;
		counter = 1;
		while (counter < k) {
			temp = next.next;
			next.next = cur;
			cur = next;
			next = temp;
			counter++;// after one iteration: cur->head; next->remains...
		}
		head.next = reverseKGroup(next, k);
		return cur;
	}

	// 函数运行完一次以后，cur变成实际上的head，而head因为没有被操作过，实际已经成为当前K个Nodes的尾巴
	// 所以才会有head.next=reverseKGroup(next, k);

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public static void main(String[] args){
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next.next = new ListNode(6);
		ReverseNodesInKGroup test = new ReverseNodesInKGroup();
		test.reverseKGroup(head, 3);
	}
}
