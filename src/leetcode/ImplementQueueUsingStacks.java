package leetcode;

import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 * 
 * push(x) -- Push element x to the back of queue.
 * 
 * pop() -- Removes the element from in front of queue.
 * 
 * peek() -- Get the front element.
 * 
 * empty() -- Return whether the queue is empty.
 */
public class ImplementQueueUsingStacks {

	class MyQueue {

		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();

		// Push element x to the back of queue.
		public void push(int x) {
			s1.push(x);
		}

		// Removes the element from in front of queue.
		public void pop() {
			peek();
			s2.pop();
		}

		// Get the front element.
		public int peek() {
			if (s2.empty())
				while (!s1.empty())
					s2.push(s1.pop());
			return s2.peek();
		}

		// Return whether the queue is empty.
		public boolean empty() {
			return s1.empty() && s2.empty();
		}
	}
}
