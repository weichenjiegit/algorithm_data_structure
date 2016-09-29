package leetcode;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that
 * the new number is the smallest possible.
 * 
 * Note:
 * 
 * The length of num is less than 10002 and will be ≥ k.
 * 
 * The given num does not contain any leading zero.
 * 
 */
public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (int i = 0; i < num.length(); i++) {
			int digit = num.charAt(i) - '0';
			while (!stack.isEmpty() && stack.peek() > digit && k > 0) {
				stack.pop();
				k--;
			}
			stack.push(digit);
		}
		while (k > 0) {
			stack.pop();
			k--;
		}
		StringBuilder res = new StringBuilder();
		while (!stack.isEmpty()) {
			res.insert(0, stack.pop());
		}
		int index = 0;
		while (index < res.length() && res.charAt(index) == '0') { // remove leading 0s
			index++;
		}
		if (index == res.length()) {
			return "0";
		}
		return res.toString().substring(index);
	}
}
