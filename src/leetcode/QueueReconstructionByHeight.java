package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair
 * of integers (h, k), where h is the height of the person and k is the number of people in front of
 * this person who have a height greater than or equal to h. Write an algorithm to reconstruct the
 * queue.
 * 
 * Note: The number of people is less than 1,100.
 */
public class QueueReconstructionByHeight {
	public int[][] reconstructQueue(int[][] people) {
		// pick up the tallest guy first
		// when insert the next tall guy, just need to insert him into kth position
		// repeat until all people are inserted into list
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? -o1[0] + o2[0] : o1[1] - o2[1];
			}
		});
		List<int[]> res = new LinkedList<>();
		for (int[] cur : people) {
			res.add(cur[1], cur);
		}
		return res.toArray(new int[people.length][]);
	}

	// Same algorithm.
	public int[][] reconstructQueue2(int[][] people) {
		List<int[]> list = new LinkedList<>();
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(1, new PairComp());
		for (int[] ppl : people) {
			queue.offer(ppl);
		}
		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			list.add(pair[1], pair);
		}
		int[][] ret = new int[people.length][];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	private class PairComp implements Comparator<int[]> {
		public int compare(int[] p1, int[] p2) {
			int comp_h = Integer.compare(p2[0], p1[0]);
			return comp_h == 0 ? Integer.compare(p1[1], p2[1]) : comp_h;
		}
	}
}
