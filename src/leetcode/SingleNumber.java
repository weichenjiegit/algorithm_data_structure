package leetcode;

import java.util.Arrays;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you implement it without
 * using extra memory?
 *
 */
public class SingleNumber {
	public int singleNumber(int[] A) {
		Arrays.sort(A);
		for (int i = 0; i < A.length - 1; i += 2) {
			if (A[i] != A[i + 1])
				return A[i];
		}
		return A[A.length - 1];
	}

	// XOR operation to store value without additional space
	/*
	 * c = a ^ b a = b ^ c b = a ^ c so, a ^ b ^ a = b
	 */
	public int singleNumber2(int[] A) {
		int res = 0;
		for (int i = 0; i < A.length; i++)
			res ^= A[i];
		return res;
	}

	public static void main(String[] args) {
		SingleNumber test = new SingleNumber();
		test.singleNumber2(new int[] { 1, 3, 1, -1, 3 });
	}
}
