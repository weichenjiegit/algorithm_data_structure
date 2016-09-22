package leetcode;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize
 * the product of those integers. Return the maximum product you can get.
 * 
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * 
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class IntegerBreak {
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
			}
		}
		return dp[n];
	}

	/**
	 * Then how many 3s you want to have? As many as possible unless you produce a 1. If you have a
	 * 1, then substitute one 3-and-1 pair with a 2-and-2 pair.
	 */
	public int integerBreak2(int n) {
		if (n == 2) {
			return 1;
		} else if (n == 3) {
			return 2;
		}
		int k = n / 3;
		int remainder = n % 3;
		if (remainder == 1) {
			return 2 * 2 * (int) (Math.pow(3, k - 1));
		} else if (remainder == 2) {
			return 2 * (int) (Math.pow(3, k));
		} else {
			return (int) (Math.pow(3, k));
		}
	}

	public int integerBreak3(int n) {
		if (n == 2)
			return 1;
		if (n == 3)
			return 2;
		int product = 1;
		while (n > 4) { // when remainder is 4, we use 2 * 2
			product *= 3;
			n -= 3;
		}
		product *= n;

		return product;
	}
}
