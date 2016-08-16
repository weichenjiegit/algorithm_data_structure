package leetcode;

/**
 * Divide two integers without using multiplication, division and mod operator.
 */
public class DivideTwoIntegers {
	// dividend = divisor*2^0 + divisor*2^1 + ... + divisor*2^n + mod; (mod<divisor)
	public int divide(int dividend, int divisor) {
		if (dividend == 0 || divisor == 0)
			return 0;
		int sign = 1;
		if (dividend < 0)
			sign = -sign;
		if (divisor < 0)
			sign = -sign;
		long dd;
		long dr;
		if (dividend == Integer.MIN_VALUE)
			dd = (long) Integer.MAX_VALUE + 1;
		else
			dd = Math.abs(dividend);
		if (divisor == Integer.MIN_VALUE)
			dr = (long) Integer.MAX_VALUE + 1;
		else
			dr = Math.abs(divisor);
		long count = 1;
		long num = dr;
		while (num < dd) {
			num <<= 1;
			count <<= 1;
		}
		int result = 0;
		while (num >= dr) {
			while (dd >= num) {
				dd -= num;
				result += count;
			}
			num >>= 1;
			count >>= 1;
		}
		if (sign < 0)
			return -result;
		else
			return result;
	}
}
