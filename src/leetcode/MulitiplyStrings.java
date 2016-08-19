package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two numbers represented as strings,
 * return multiplication of the numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 */
public class MulitiplyStrings {
	public String multiply(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		List<Integer> cache = new ArrayList<>(len1 + len2);
		// cache用来存储每一位当前的结果， 0 + 0 是个位，0 + 1 or 1 + 0是十位......
		for (int i = 0; i < len1 + len2; i++)
			cache.add(0);
		for (int i = 0; i < len1; i++) {
			int n1 = num1.charAt(len1 - 1 - i) - '0';
			int carry = 0;
			for (int j = 0; j < len2; j++) {
				int n2 = num2.charAt(len2 - 1 - j) - '0';
				carry = n1 * n2 + carry + cache.get(i + j);
				cache.set(i + j, carry % 10);
				carry /= 10;
			}
			cache.set(i + len2, carry);
		}

		int i = len1 + len2 - 1;
		while (i > 0 && cache.get(i) == 0)
			i--;
		StringBuilder sb = new StringBuilder("");
		while (i >= 0)
			sb.append(cache.get(i--));
		return sb.toString();
	}
}
