package leetcode;

/**
 *  A linked list is given
 *  such that each node contains an additional random pointer
 *  which could point to any node in the list or null.
 *  
 *  Return a deep copy of the list. 
 */
public class CopyListWithRandomPointer {

	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return head;
		duplicateList(head);
		return adjustList(head);
	}

	public void duplicateList(RandomListNode head) {
		RandomListNode node = head;
		// copy each node and append it to the original one
		while (node != null) {
			RandomListNode temp = new RandomListNode(node.label);
			temp.next = node.next;
			node.next = temp;
			node = temp.next;
		}
	}

	public RandomListNode adjustList(RandomListNode head) {
		RandomListNode newHead = head.next;
		RandomListNode node = head;
		RandomListNode newNode = newHead;
		// adjust the random pointer of the copied nodes
		while (node != null) {
			if (node.random == null)
				node.next.random = null; // not necessary
			else
				node.next.random = node.random.next;
			node = node.next.next;
		}
		node = head;
		newNode = newHead;
		// separate the list to two lists
		while (node != null) {
			if (newNode.next != null) {
				node.next = newNode.next;
				newNode.next = newNode.next.next;
				node = node.next;
				newNode = newNode.next;
			} else {
				node.next = null;
				newNode.next = null;
				node = node.next;
				newNode = newNode.next;
			}
		}
		return newHead;
	}

	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}
}
