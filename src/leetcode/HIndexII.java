package leetcode;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you
 * optimize your algorithm?
 * 
 * Hint: Expected runtime complexity is in O(log n) and the input is sorted
 */
public class HIndexII {
	public int hIndex(int[] citations) {
		if (citations == null || citations.length == 0)
			return 0;
		int l = 0, r = citations.length;
		int n = citations.length;
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (citations[mid] == n - mid) // n - mid means number of papers has at least
			                               // citations[mid] citations.
				return n - mid;
			if (citations[mid] < n - mid)
				l = mid + 1;
			else
				r = mid;
		}
		return n - l;
	}
}
