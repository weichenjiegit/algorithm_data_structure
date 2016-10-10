package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * 
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that
 * calls are being made to the system in chronological order (ie, the timestamp is monotonically
 * increasing). You may assume that the earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 */
public class DesignHitCounter {
	public class HitCounter {
		Queue<Integer> q = null;

		/** Initialize your data structure here. */
		public HitCounter() {
			q = new LinkedList<Integer>();
		}

		/**
		 * Record a hit.
		 * 
		 * @param timestamp
		 *            - The current timestamp (in seconds granularity).
		 */
		public void hit(int timestamp) {
			q.offer(timestamp);
		}

		/**
		 * Return the number of hits in the past 5 minutes.
		 * 
		 * @param timestamp
		 *            - The current timestamp (in seconds granularity).
		 */
		public int getHits(int timestamp) {
			while (!q.isEmpty() && timestamp - q.peek() >= 300) {
				q.poll();
			}
			return q.size();
		}
	}

	public class HitCounter2 {
		private int[] times;
		private int[] hits;

		/** Initialize your data structure here. */
		public HitCounter2() {
			times = new int[300];
			hits = new int[300];
		}

		/**
		 * Record a hit.
		 * 
		 * @param timestamp
		 *            - The current timestamp (in seconds granularity).
		 */
		public void hit(int timestamp) {
			int index = timestamp % 300;
			if (times[index] != timestamp) {
				times[index] = timestamp;
				hits[index] = 1;
			} else {
				hits[index]++;
			}
		}

		/**
		 * Return the number of hits in the past 5 minutes.
		 * 
		 * @param timestamp
		 *            - The current timestamp (in seconds granularity).
		 */
		public int getHits(int timestamp) {
			int total = 0;
			for (int i = 0; i < 300; i++) {
				if (timestamp - times[i] < 300) {
					total += hits[i];
				}
			}
			return total;
		}
	}
}
