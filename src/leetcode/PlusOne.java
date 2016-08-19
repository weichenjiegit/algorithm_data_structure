package leetcode;

/**
 * Given a number represented as an array of digits, plus one to the number.
 */
import java.util.ArrayList;
import java.util.List;

public class PlusOne {
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		// 9****9 + 1 case
		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;
		return newNumber;
	}

	public int[] plusOne2(int[] digits) {
		List<Integer> res = new ArrayList<>();
		int carry = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			int sum = carry + digits[i];
			res.add(sum % 10);
			carry = sum / 10;
		}
		if (carry != 0) {
			res.add(carry);
		}
		int[] result = new int[res.size()];
		for (int i = 0; i < res.size(); i++)
			result[i] = res.get(res.size() - 1 - i);
		return result;
	}
}
