package leetcode;

/**
 * The gray code is a binary numeral system where two successive values
 * differ in only one bit.
 * Given a non-negative integer n representing
 * the total number of bits in the code,
 * print the sequence of gray code. A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence
 * according to the above definition.
 * For now, the judge is able to judge based on
 *  one instance of gray code sequence. Sorry about that.
 *  
 *  二进制码->格雷码（编码）：从最右边一位起，依次将每一位与左边一位异或（XOR），
 *  作为对应格雷码该位的值，最左边一位不变（相当于左边是0）；
 *  
 *  格雷码->二进制码（解码）：从左边第二位起，将每位与左边一位解码后的值异或，
 *  作为该位解码后的值（最左边一位依然不变）。
 */
import java.util.ArrayList;
import java.util.List;

public class GrayCode {
	/**
	 * when n=3, we can get the result based on n=2. 00,01,11,10 -> (000,001,011,010 )
	 * (110,111,101,100). The middle two numbers only differ at their highest bit, while the rest
	 * numbers of part two are exactly symmetric of part one.
	 */
	public List<Integer> grayCode(int n) {
		List<Integer> rs = new ArrayList<>();
		rs.add(0);
		for (int i = 0; i < n; i++) {
			int size = rs.size();
			for (int k = size - 1; k >= 0; k--)
				rs.add(rs.get(k) | 1 << i);
		}
		return rs;
	}

	// Not suitable for interview
	public List<Integer> grayCode2(int n) {
		List<Integer> res = new ArrayList<>();
		int size = 1 << n;
		// 1 left shifted with n digits
		for (int i = 0; i < size; i++) {
			res.add((i >> 1) ^ i);
		}
		return res;
	}

	public static void main(String[] args) {
		GrayCode test = new GrayCode();
		test.grayCode(3);
		test.decode(3);
	}

	public int decode(int num) {
		int mask;
		for (mask = num >> 1; mask != 0; mask = mask >> 1)
			num = num ^ mask;
		return num;
	}
}
