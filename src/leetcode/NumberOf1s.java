package leetcode;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also
 * known as the Hamming weight).
 * 
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3
 */
public class NumberOf1s {

	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int result = 0;
		while (n != 0) {
			if ((n & 0x1) == 1) {
				result++;
			}
			n >>>= 1;
		}
		return result;
	}

	public int hammingWeight2(int n) {
		int ones = 0;
		while (n != 0) {
			ones = ones + (n & 1);
			n = n >>> 1;
		}
		return ones;
	}

	/**
	 * flip the least-significant 1-bit of the number to 0, and add 111 to the sum.
	 */
	public int hammingWeight3(int n) {
		int sum = 0;
		while (n != 0) {
			sum++;
			n &= (n - 1);
		}
		return sum;
	}
}
