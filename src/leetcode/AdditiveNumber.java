package leetcode;

/**
 * Additive number is a string whose digits can form additive sequence.
 * 
 * A valid additive sequence should contain at least three numbers. Except for the first two
 * numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * 
 * For example: "112358" is an additive number because the digits can form an additive sequence: 1,
 * 1, 2, 3, 5, 8.
 * 
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 
 * 1 + 99 = 100, 99 + 100 = 199
 * 
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02,
 * 3 is invalid.
 * 
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive
 * number.
 * 
 * Follow up: How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {
	public boolean isAdditiveNumber(String num) {
		int L = num.length();

		// choose the first number A
		for (int i = 1; i <= (L - 1) / 2; i++) {
			// A cannot start with a 0 if its length is more than 1
			if (num.charAt(0) == '0' && i >= 2)
				break; // previous code: continue;

			// choose the second number B
			for (int j = i + 1; L - j >= j - i && L - j >= i; j++) {
				// B cannot start with a 0 if its length is more than 1
				if (num.charAt(i) == '0' && j - i >= 2)
					break; // previous: continue;

				long num1 = Long.parseLong(num.substring(0, i)); // A
				long num2 = Long.parseLong(num.substring(i, j)); // B
				String substr = num.substring(j); // remaining string

				if (isAdditive(substr, num1, num2))
					return true; // return true if passes isAdditive test
				// else continue; // continue for loop if does not pass isAdditive test
			}
		}
		return false; // does not pass isAdditive test, thus is not additive
	}

	// Recursively checks if a string is additive
	private boolean isAdditive(String str, long num1, long num2) {
		if (str.equals(""))
			return true; // reaches the end of string means a yes

		long sum = num1 + num2;
		String s = ((Long) sum).toString();
		if (!str.startsWith(s))
			return false; // if string does not start with sum of num1 and num2, returns false

		return isAdditive(str.substring(s.length()), num2, sum); // recursively checks the remaining
		                                                         // string
	}

	// Solution 2. Same algorithm.
	public boolean isAdditiveNumber2(String num) {
		int n = num.length();
		for (int i = 1; i <= n / 2; ++i)
			for (int j = 1; Math.max(j, i) <= n - i - j; ++j)
				if (isValid(i, j, num))
					return true;
		return false;
	}

	private boolean isValid(int i, int j, String num) {
		if (num.charAt(0) == '0' && i > 1)
			return false;
		if (num.charAt(i) == '0' && j > 1)
			return false;
		String sum;
		Long x1 = Long.parseLong(num.substring(0, i));
		Long x2 = Long.parseLong(num.substring(i, i + j));
		for (int start = i + j; start != num.length(); start += sum.length()) {
			x2 = x2 + x1;
			x1 = x2 - x1;
			sum = x2.toString();
			if (!num.startsWith(sum, start))
				return false;
		}
		return true;
	}
}
