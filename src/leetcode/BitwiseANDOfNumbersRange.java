package leetcode;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in
 * this range, inclusive.
 * 
 * For example, given the range [5, 7], you should return 4.
 *
 */
public class BitwiseANDOfNumbersRange {
	/**
	 * <pre>
	 * 1. last bit of (odd number & even number) is 0.
	 * 2. when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
	 * 3. Move m and n right a position.
	 * </pre>
	 */
	public int rangeBitwiseAnd(int m, int n) {
		int count = 0;
		while (m != n) {
			m >>= 1;
			n >>= 1;
			count++;
		}
		return m <<= count; // get all the trailing zeroes.
	}

}
