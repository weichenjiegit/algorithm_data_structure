package leetcode;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * <pre>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * </pre>
 */
public class MinStack {

	private int min = Integer.MAX_VALUE;
	private Stack<Integer> stack = new Stack<>();

	public void push(int x) {
		// only push the old minimum value when the current
		// minimum value changes after pushing the new value x
		if (x <= min) {
			stack.push(min);
			min = x;
		}
		stack.push(x);
	}

	public void pop() {
		// if pop operation could result in the changing of the current minimum value,
		// pop twice and change the current minimum value to the last minimum value.
		if (stack.peek() == min) {
			stack.pop();
			min = stack.pop();
		} else {
			stack.pop();
		}
		if (stack.empty()) {
			min = Integer.MAX_VALUE;
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return min;
	}
}
