package leetcode;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits, determine the total
 * number of ways to decode it. For example, Given encoded message "12",it could be decoded as "AB"
 * (1 2) or "L" (12). The number of ways decoding "12" is 2.
 */
public class DecodeWays {
	public int numDecodings(String s) {
		int n = s.length();
		if (n == 0)
			return 0;
		int[] res = new int[n + 1];
		res[0] = 1;
		if (s.charAt(0) != '0') // else res[1] = 0
			res[1] = 1;
		for (int i = 2; i <= n; i++) {
			int first = Integer.valueOf(s.substring(i - 1, i));
			int second = Integer.valueOf(s.substring(i - 2, i));
			if (first >= 1 && first <= 9) {
				res[i] += res[i - 1];
			}
			if (second >= 10 && second <= 26) {
				res[i] += res[i - 2];
			}
		}
		return res[n];
	}

	public static void main(String[] args) {
		DecodeWays test = new DecodeWays();
		test.numDecodings("10");
	}
}
