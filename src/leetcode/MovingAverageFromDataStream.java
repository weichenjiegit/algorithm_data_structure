package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the
 * sliding window.
 */
public class MovingAverageFromDataStream {
	public class MovingAverage {
		private int[] window;
		private int n, insert;
		private long sum;

		/** Initialize your data structure here. */
		public MovingAverage(int size) {
			window = new int[size];
			insert = 0;
			sum = 0;
		}

		public double next(int val) {
			if (n < window.length)
				n++;
			sum -= window[insert];
			sum += val;
			window[insert] = val;
			insert = (insert + 1) % window.length;

			return (double) sum / n;
		}
	}

	public class MovingAverage2 {
		private double previousSum = 0.0;
		private int maxSize;
		private Queue<Integer> currentWindow;

		public MovingAverage2(int size) {
			currentWindow = new LinkedList<Integer>();
			maxSize = size;
		}

		public double next(int val) {
			if (currentWindow.size() == maxSize) {
				previousSum -= currentWindow.remove();
			}

			previousSum += val;
			currentWindow.add(val);
			return previousSum / currentWindow.size();
		}
	}
}
