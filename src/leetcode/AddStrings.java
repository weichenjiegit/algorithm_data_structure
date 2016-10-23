package leetcode;

/**
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and
 * num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 5100.
 * 
 * Both num1 and num2 contains only digits 0-9.
 * 
 * Both num1 and num2 does not contain any leading zero.
 * 
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {
	public String addStrings(String num1, String num2) {
		StringBuffer sb = new StringBuffer();
		int i = num1.length() - 1;
		int j = num2.length() - 1;
		int carry = 0;
		while (i >= 0 || j >= 0) {
			int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
			int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
			int sum = n1 + n2 + carry;
			sb.insert(0, sum % 10);
			if (sum >= 10) {
				carry = sum / 10;
			} else {
				carry = 0;
			}
			i--;
			j--;
		}
		if (carry > 0) {
			sb.insert(0, carry);
		}
		return sb.toString();

	}
}
