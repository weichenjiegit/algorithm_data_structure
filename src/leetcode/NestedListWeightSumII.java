package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their
 * depth.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other
 * lists.
 * 
 * Different from the previous question where weight is increasing from root to leaf, now the weight
 * is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level
 * integers have the largest weight.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * 
 * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6
 * at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
public class NestedListWeightSumII {

	public int depthSumInverse(List<NestedInteger> nestedList) {
		if (nestedList == null)
			return 0;
		Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
		int prev = 0;
		int total = 0;
		for (NestedInteger next : nestedList) {
			queue.offer(next);
		}

		while (!queue.isEmpty()) {
			int size = queue.size();
			int levelSum = 0;
			for (int i = 0; i < size; i++) {
				NestedInteger current = queue.poll();
				if (current.isInteger())
					levelSum += current.getInteger();
				else {
					List<NestedInteger> nextList = current.getList();
					if (nextList != null) {
						for (NestedInteger next : nextList) {
							queue.offer(next);
						}
					}
				}
			}
			prev += levelSum; // If there is a deeper level, pre sum will get added one more time.
			total += prev;
		}
		return total;
	}

	public interface NestedInteger {
		// Constructor initializes an empty nested list.
		// public NestedInteger();

		// Constructor initializes a single integer.
		// public NestedInteger(int value);

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value);

		// Set this NestedInteger to hold a nested list and adds a nested integer to it.
		public void add(NestedInteger ni);

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
}
