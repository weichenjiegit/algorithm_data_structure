package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the
 * sorted order, not the kth distinct element.
 * 
 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInAnArray {

	public int findKthLargest(int[] nums, int k) {
		final int N = nums.length;
		Arrays.sort(nums);
		return nums[N - k];
	}

	// Max heap
	public int findKthLargest2(int[] nums, int k) {
		final PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int val : nums) {
			pq.offer(val);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}

	// Quicksort algorithm. O(n) best case. O(n ^ 2) worst case.
	public int findKthLargest3(int[] nums, int k) {
		k = nums.length - k;
		int lo = 0;
		int hi = nums.length - 1;
		while (lo < hi) {
			final int j = partition(nums, lo, hi);
			if (j < k) {
				lo = j + 1;
			} else if (j > k) {
				hi = j - 1;
			} else {
				break;
			}
		}
		return nums[k];
	}

	private int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		while (true) {
			while (i < hi && less(a[++i], a[lo]))
				;
			while (j > lo && less(a[lo], a[--j]))
				;
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, lo, j);
		return j;
	}

	private void swap(int[] a, int i, int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private boolean less(int v, int w) {
		return v < w;
	}
}
