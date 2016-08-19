package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {

	// Sorting. O(nlogn).
	public List<Interval> merge(List<Interval> intervals) {
		int size = intervals.size();
		List<Interval> res = new ArrayList<>();
		if (size < 1)
			return res;
		Collections.sort(intervals, comp);
		Interval cur = intervals.get(0);
		Interval next;
		for (int i = 1; i < size; i++) {
			next = intervals.get(i);
			if (next.start > cur.end) {
				res.add(new Interval(cur.start, cur.end));
				cur = next;
			} else {
				cur.end = cur.end > next.end ? cur.end : next.end;
			}
		}
		res.add(new Interval(cur.start, cur.end));
		return res;
	}

	public static Comparator<Interval> comp = new Comparator<Interval>() {
		public int compare(Interval i1, Interval i2) {
			return Integer.compare(i1.start, i2.start);
		}
	};

	// No sorting. O(n^2).
	public List<Interval> merge2(List<Interval> intervals) {
		List<Interval> res = new ArrayList<Interval>();

		for (Interval in_interval : intervals) {
			if (res.isEmpty()) {
				res.add(in_interval);
				continue;
			}

			Interval to_merge = in_interval;
			while (to_merge != null) {
				Interval updated_interval = null;
				for (Interval res_interval : res) {
					if (areOverlap(to_merge, res_interval)) {
						res_interval.start = Math.min(res_interval.start, to_merge.start);
						res_interval.end = Math.max(res_interval.end, to_merge.end);
						updated_interval = res_interval;
						break;
					}
				}

				if (updated_interval != null) {
					to_merge = updated_interval;
					res.remove(updated_interval);
				} else {
					res.add(to_merge);
					to_merge = null;
				}
			}
		}

		return res;
	}

	boolean areOverlap(Interval interval1, Interval interval2) {
		return !(interval1.end < interval2.start || interval1.start > interval2.end);
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
