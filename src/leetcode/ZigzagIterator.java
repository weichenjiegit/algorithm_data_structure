package leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * 
 * For example, given two 1d vectors:
 * 
 * v1 = [1, 2] v2 = [3, 4, 5, 6]
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next
 * should be: [1, 3, 2, 4, 5, 6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 */
public class ZigzagIterator {
	LinkedList<Iterator<Integer>> list;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		list = new LinkedList<Iterator<Integer>>();
		if (!v1.isEmpty())
			list.add(v1.iterator());
		if (!v2.isEmpty())
			list.add(v2.iterator());
	}

	public int next() {
		Iterator<Integer> poll = list.remove();
		int result = poll.next();
		if (poll.hasNext())
			list.add(poll);
		return result;
	}

	public boolean hasNext() {
		return !list.isEmpty();
	}
}
