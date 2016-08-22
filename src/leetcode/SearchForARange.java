package leetcode;

public class SearchForARange {
	public int[] searchRange(int[] A, int target) {
		int[] res = { -1, -1 };
		if (A.length == 0)
			return res;
		int begin = 0;
		int end = A.length - 1;
		while (begin < end) {
			int mid = begin + (end - begin) / 2;
			if (A[mid] >= target)
				end = mid;
			else
				begin = mid + 1;
		}
		if (A[begin] != target)
			return res;
		else
			res[0] = begin;

		begin = 0;
		end = A.length - 1;
		while (begin < end) {
			int mid = begin + (end - begin + 1) / 2;
			if (A[mid] <= target)
				begin = mid;
			else
				end = mid - 1;
		}
		if (A[end] != target)
			return res;
		else
			res[1] = end;

		return res;
	}

	// Recursive solution
	public int[] searchRange(int A[], int n, int target) {
		int[] result = {-1, -1};
		result[0] = findPosition(A, 0, n - 1, target, true);
		result[1] = findPosition(A, 0, n - 1, target, false);
		return result;

	}

	int findPosition(int A[], int begin, int end, int target, boolean leftFlag) {
		if (begin > end)
			return -1;
		int middle = (begin + end) / 2;
		if (A[middle] == target) {
			int position;
			if (leftFlag) {
				position = findPosition(A, begin, middle - 1, target, leftFlag);
			} else {
				position = findPosition(A, middle + 1, end, target, leftFlag);
			}
			return (position == -1) ? middle : position;
		} else if (A[middle] < target) {
			return findPosition(A, middle + 1, end, target, leftFlag);
		} else {
			return findPosition(A, begin, middle - 1, target, leftFlag);
		}
	}

	public static void main(String[] args) {
		int[] a = {1,2,3,3,3,3,4,5,9};
		SearchForARange test = new SearchForARange();
		test.searchRange(a, a.length, 3);
	}
}
