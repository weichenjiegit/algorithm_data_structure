package leetcode;

/**
 * Given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class JumpGameII {
	// DP
	public int jump(int[] A) {
		int[] res = new int[A.length];
		res[0] = 0;
		for (int i = 1; i < res.length; i++)
			res[i] = A.length;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j <= A[i]; j++) {
				if (i + j < A.length && res[i] + 1 < res[i + j])
					res[i + j] = res[i] + 1;
			}
		}
		return res[A.length - 1];
	}

	// Greed
	public int jump2(int[] A) {
		if (A.length <= 1)
			return 0;
		int i = 0;
		int minStep = 0;
		int curFar = 0;
		while (i < A.length) {
			curFar = i + A[i];
			minStep++;
			if (curFar >= A.length - 1)
				return minStep;
			int tempFar = 0;
			for (int j = i + 1; j <= curFar; j++) {
				if (j + A[j] > tempFar) {
					tempFar = j + A[j];
					i = j;
				}
			}
		}
		return minStep;
	}

	public static void main(String[] args) {
		int[] A = { 3, 1, 1, 0, 0, 3};
		JumpGameII test = new JumpGameII();
		test.jump2(A);
	}
}
