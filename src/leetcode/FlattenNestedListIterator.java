package leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other
 * lists.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next
 * should be: [1,1,2,1,1].
 * 
 * Example 2: Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next
 * should be: [1,4,6].
 */
public class FlattenNestedListIterator {

	public class NestedIterator implements Iterator<Integer> {

		Stack<NestedInteger> stack = new Stack<>();

		public NestedIterator(List<NestedInteger> nestedList) {
			for (int i = nestedList.size() - 1; i >= 0; i--) {
				stack.push(nestedList.get(i));
			}
			locateNext();
		}

		private void locateNext() {
			while (!stack.isEmpty()) {
				NestedInteger curr = stack.peek();
				if (curr.isInteger()) {
					return;
				}
				stack.pop();
				for (int i = curr.getList().size() - 1; i >= 0; i--) {
					stack.push(curr.getList().get(i));
				}
			}
		}

		@Override
		public Integer next() {
			Integer value = stack.pop().getInteger();
			locateNext();
			return value;
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	}

	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
}
