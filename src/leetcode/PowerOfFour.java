package leetcode;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 
 * Example: Given num = 16, return true. Given num = 5, return false.
 * 
 * Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {
	// 5 in binary is 0101
	public boolean isPowerOfFour(int num) {
		return num > 0 && (0x55555555 & num) == num && ((num - 1) & num) == 0;
	}

	public boolean isPowerOfFour2(int num) {
		double epsilon = 0.0000000001;
		long l = Integer.toUnsignedLong(num);
		return ((Math.log10(l) / Math.log10(4)) % 1) < epsilon;
	}
}
