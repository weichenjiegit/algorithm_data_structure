package leetcode;

/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 */
public class SearchInRotatedSortedArrayII {
	public boolean search(int[] A, int target) {
		int len = A.length;
		if (len < 1)
			return false;
		int start = 0, end = len - 1, mid;
		while (start <= end) {
			mid = start + (end - start) / 2;
			if (A[mid] == target)
				return true;
			if (A[start] < A[mid]) {// sorted left, unsorted right
				if (target >= A[start] && target <= A[mid])
					end = mid - 1;
				else
					start = mid + 1;
			} else if (A[start] > A[mid]) {// unsorted left, sorted right
				if (target >= A[mid] && target <= A[end])
					start = mid + 1;
				else
					end = mid - 1;
			} else
				// skip duplicate one, A[start] = A[mid]
				start++;
		}
		return false;
	}
}
