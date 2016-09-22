package leetcode;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else
 * False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * 
 * Input: 16 Returns: True
 * 
 * Example 2:
 * 
 * Input: 14 Returns: False
 */
public class ValidPerfectSquare {
	/**
	 * 1 = 1
	 * 
	 * 4 = 1 + 3
	 * 
	 * 9 = 1 + 3 + 5
	 * 
	 * 16 = 1 + 3 + 5 + 7
	 * 
	 * 25 = 1 + 3 + 5 + 7 + 9
	 * 
	 * 36 = 1 + 3 + 5 + 7 + 9 + 11
	 * 
	 * ....
	 * 
	 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = n * n
	 * 
	 * 
	 * Let x be the number of iterations needed to solve the problem, and let Σ be the sum from i =
	 * 1 to x.
	 * 
	 * Σ(1 + 2i) = n =>
	 * 
	 * x + 2Σi = n =>
	 * 
	 * x + 2(x(x+1)) = n =>
	 * 
	 * 2x^2 + 3x = n =>
	 * 
	 * x = [-3 +/- sqrt(9 + 8n)]/4 =>
	 * 
	 * you can see that n is in a square root term, so the complexity should be O(sqrt(n)).
	 */
	public boolean isPerfectSquare(int num) {
		int i = 1;
		while (num > 0) {
			num -= i;
			i += 2;
		}
		return num == 0;
	}

	// Newton method.
	public boolean isPerfectSquare2(int num) {
		long x = num;
		while (x * x > num) {
			x = (x + num / x) >> 1;
		}
		return x * x == num;
	}

	// O(log n)
	public boolean isPerfectSquare3(int num) {
		int low = 1, high = num;
		while (low <= high) {
			long mid = (low + high) >>> 1;
			if (mid * mid == num) {
				return true;
			} else if (mid * mid < num) {
				low = (int) mid + 1;
			} else {
				high = (int) mid - 1;
			}
		}
		return false;
	}
}
