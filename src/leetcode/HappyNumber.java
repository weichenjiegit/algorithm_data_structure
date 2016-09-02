package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 * 
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the
 * number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * 
 * <pre>
 * Example: 19 is a happy number
 * 
 *     1 ^ 2 + 9 ^ 2 = 82
 *     8 ^ 2 + 2 ^ 2 = 68
 *     6 ^ 2 + 8 ^ 2 = 100
 *     1 ^ 2 + 0 ^ 2 + 0 ^ 2 = 1
 * </pre>
 */
public class HappyNumber {
	public boolean isHappy(int n) {
		Set<Integer> inLoop = new HashSet<>();
		int squareSum, remain;
		while (!inLoop.contains(n)) {
			inLoop.add(n);
			squareSum = 0;
			while (n > 0) {
				remain = n % 10;
				squareSum += remain * remain;
				n /= 10;
			}
			if (squareSum == 1)
				return true;
			else
				n = squareSum;
		}
		return false;
	}

	public boolean isHappy2(int n) {
		int slow, fast;
		slow = fast = n;
		do {
			slow = digitSquareSum(slow);
			fast = digitSquareSum(fast);
			fast = digitSquareSum(fast);
			if (fast == 1) return true;
		} while (slow != fast);
		return false; // slow == fast != 1
	}

	private int digitSquareSum(int n) {
		int sum = 0, tmp;
		while (n != 0) {
			tmp = n % 10;
			sum += tmp * tmp;
			n /= 10;
		}
		return sum;
	}
}
