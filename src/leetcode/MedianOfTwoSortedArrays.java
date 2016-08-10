package leetcode;

/**
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 */
public class MedianOfTwoSortedArrays {
	// Solution 1 O(lg(m + n))
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length, n = B.length;
		if (m < n)// suppose median in A
			return findMedian(A, B, 0, m - 1);
		else
			// suppose median in B
			return findMedian(B, A, 0, n - 1);
	}

	private double findMedian(int A[], int B[], int left, int right) {
		int m = A.length, n = B.length, mid = (m + n) / 2;
		if (left > right) {
			return findMedian(B, A, Math.max(0, mid - m), Math.min(n - 1, mid));
		}

		int i = (left + right) / 2, j = mid - i - 1;
		// Suppose median is A[i], it is greater than i - 1 elements in A
		// then, A[i] is greater than mid - i - 1 elements in B
		// i + j + 1 = mid
		if (j >= 0 && A[i] < B[j]) // A[i] < median < B[j]
			return findMedian(A, B, i + 1, right);
		if (j < n - 1 && A[i] > B[j + 1]) // A[i] > median > B[j + 1]
			return findMedian(A, B, left, i - 1);
		// found median
		// which means B[j] <= A[i] <= B[j + 1]
		// m + n is odd
		if (((m + n) & 0x1) > 0 || (i <= 0 && (j < 0 || j >= n)))
			return A[i];
		// m+n is even
		if (j < 0 || j >= n)
			return (A[i] + A[i - 1]) / 2.0;
		if (i <= 0)
			return (A[i] + B[j]) / 2.0;
		return (A[i] + Math.max(B[j], A[i - 1])) / 2.0;
	}

	// Solution 2
	// through kth smallest function
	public double findMedianSortedArrays2(int A[], int B[]) {
		int m = A.length, n = B.length;
		if ((m + n) % 2 == 0)
			return (findKthSmallest(A, m, B, n, (m + n) / 2) + findKthSmallest(A, m, B, n, (m + n) / 2 + 1)) / 2.0;
		else
			return findKthSmallest(A, m, B, n, (m + n) / 2 + 1);
	}

	public double findKthSmallest(int A[], int m, int B[], int n, int k) {
		int ia = 0, ib = 0, res = 0;
		while (--k >= 0 && ia < m && ib < n) {
			if (A[ia] < B[ib])
				res = A[ia++];
			else
				res = B[ib++];
		}
		while (ia < m && k-- >= 0)
			res = A[ia++];
		while (ib < n && k-- >= 0)
			res = B[ib++];
		return res;
	}
}
