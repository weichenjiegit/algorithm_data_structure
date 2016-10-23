package leetcode;

/**
 * A sequence of number is called arithmetic if it consists of at least three elements and if the
 * difference between any two consecutive elements is the same.
 * 
 * <pre>
 * For example, these are arithmetic sequence:
 * 
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 
 * The following sequence is not arithmetic.
 * 
 * 1, 1, 2, 5, 7
 * 
 * A zero-indexed array A consisting of N numbers is given.
 * A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 * 
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * 
 * The function should return the number of arithmetic slices in the array A.
 * 
 * Example:
 * 
 * A = [1, 2, 3, 4]
 * 
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 * </pre>
 */
public class ArthmeticSlices {
	/**
	 * The idea is pretty simple think about 1,2,3,4,5...,
	 * 
	 * if we stop at 3 we have 1 option; if we stop at 4 we have 3 options; if we stop at 5 we have
	 * 5 option; . . .
	 * 
	 * so if we know how many options we have now and how many options we have before: we can have
	 * this simple formula:
	 * 
	 * next = current*2 + 1 - previous;
	 * 
	 * The reason is pretty straightforward, thinking about from 3->4 -> 5: At 3 we have 1,2,3 At 4
	 * we have 1,2,3 | 2,3,4 | 1,2,3,4 At 5 we have 1,2,3 | 2,3,4 | 3,4,5 | 1,2,3,4 | 2,3,4,5 |
	 * 1,2,3,4,5
	 * 
	 * if we want to calculate how many options we have at 5, we know 1,2,3,4 have 3 options and
	 * 2,3,4,5 also have 3 options and 1,2,3,4,5, so now we have 7 options but we have a overlap
	 * 2,3,4 so we need to minus the overlap, which equals how many options we have at 3.
	 */
	public int numberOfArithmeticSlices(int[] A) {
		int len = A.length;
		if (len < 3)
			return 0;

		int diff = A[1] - A[0];
		int curDiff;

		int result = 0;
		int current = 0;
		int prev = 0;
		for (int i = 2; i < len; i++) {
			curDiff = A[i] - A[i - 1];
			if (curDiff == diff) {
				if (current == 0)
					current = 1;
				else {
					int temp = current;
					current = current * 2 + 1 - prev;
					prev = temp;
				}
			} else {
				diff = curDiff;
				result += current;
				current = 0;
			}
		}

		result += current;
		return result;
	}
}
