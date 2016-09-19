package leetcode;

/**
 * Given an integer, write a function to determine if it is a power of three.
 * 
 * Follow up: Could you do it without using any loop / recursion?
 */
public class PowerOfThree {

	public boolean isPowerOfThree(int n) {
		// 1162261467 is 3^19, 3^20 is bigger than int
		return (n > 0 && 1162261467 % n == 0);
	}

	public boolean isPowerOfThree2(int n) {
		return n > 0 && (int) Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3.0))) % n == 0;
	}

	public boolean isPowerOfThree3(int n) {
		double epsilon = 0.0000000001;
		return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon; 
	}
}
