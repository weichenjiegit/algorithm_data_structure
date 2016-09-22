package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers
 * seen so far as a list of disjoint intervals.
 * 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary
 * will be:
 * 
 * <pre>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * </pre>
 * 
 * Follow up: What if there are lots of merges and the number of disjoint intervals are small
 * compared to the data stream's size?
 */
public class DataStreamAsDisjointIntervals {

	public class SummaryRanges {
		TreeMap<Integer, Interval> tree;

		public SummaryRanges() {
			tree = new TreeMap<>();
		}

		public void addNum(int val) {
			if (tree.containsKey(val))
				return;
			Integer low = tree.lowerKey(val);
			Integer high = tree.higherKey(val);
			if (low != null && high != null && tree.get(low).end + 1 == val && high == val + 1) {
				tree.get(low).end = tree.get(high).end;
				tree.remove(high);
			} else if (low != null && tree.get(low).end + 1 >= val) {
				tree.get(low).end = Math.max(tree.get(low).end, val);
			} else if (high != null && high == val + 1) {
				tree.put(val, new Interval(val, tree.get(high).end));
				tree.remove(high);
			} else {
				tree.put(val, new Interval(val, val));
			}
		}

		public List<Interval> getIntervals() {
			return new ArrayList<>(tree.values());
		}
	}

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
}
