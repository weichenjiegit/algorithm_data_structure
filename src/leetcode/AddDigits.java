package leetcode;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one
 * digit.
 * 
 * For example:
 * 
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return
 * it.
 * 
 * Follow up: Could you do it without any loop/recursion in O(1) runtime?
 */
public class AddDigits {

	public int addDigits(int num) {
		if (num == 0) {
			return 0;
		}
		if (num % 9 == 0) {
			return 9;
		} else {
			return num % 9;
		}
	}

	/**
	 * 0~9 (10 nums) : 0,1,2,3,4,5,6,7,8,9
	 * 
	 * 10~18(9 nums): 1,2,3,4,5,6,7,8,9
	 * 
	 * 19~27(9 nums): 1,2,3,4,5,6,7,8,9 and so on
	 * 
	 * num = 10 * a + b = a + b + 9 * a
	 * 
	 * 10^k % 9 = 1
	 * a*10^k % 9 = a % 9
	 */
}
