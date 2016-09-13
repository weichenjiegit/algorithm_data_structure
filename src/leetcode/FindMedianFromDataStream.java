package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is
 * no middle value. So the median is the mean of the two middle value.
 * 
 * <pre>
 * Examples:
 * 
 * [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 *     void addNum(int num) - Add a integer number from the data stream to the data structure.
 *     double findMedian() - Return the median of all elements so far.
 * 
 * For example:
 * 
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3) 
 * findMedian() -> 2
 * </pre>
 */
public class FindMedianFromDataStream {
	public class MedianFinder {
		Queue<Integer> minHeap = new PriorityQueue<>();// heap is a minimal heap by default
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());// change to a
		                                                                         // maximum heap

		// Adds a number into the data structure.
		public void addNum(int num) {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
			if (maxHeap.size() < minHeap.size())
				maxHeap.offer(minHeap.poll());
		}

		// Returns the median of current data stream
		public double findMedian() {
			if (maxHeap.size() == minHeap.size())
				return (maxHeap.peek() + minHeap.peek()) / 2.0;
			else
				return maxHeap.peek();
		}
	};
}
