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
 */
public class NestedListWeightSum {

	public int depthSum(List<NestedInteger> nestedList) {
		if (nestedList == null || nestedList.size() == 0) {
			return 0;
		}
		return helper(nestedList, 1);
	}

	private int helper(List<NestedInteger> input, int weight) {
		int res = 0;
		for (NestedInteger i : input) {
			if (i.isInteger()) {
				res = res + i.getInteger() * weight;
			} else {
				res += helper(i.getList(), weight + 1);
			}
		}
		return res;
	}

	public int depthSum2(List<NestedInteger> nestedList) {
		if (nestedList == null) {
			return 0;
		}
		int sum = 0;
		int level = 1;
		Queue<NestedInteger> queue = new LinkedList<>(nestedList);
		while (queue.size() > 0) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				NestedInteger ni = queue.poll();

				if (ni.isInteger()) {
					sum += ni.getInteger() * level;
				} else {
					queue.addAll(ni.getList());
				}
			}
			level++;
		}
		return sum;
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
