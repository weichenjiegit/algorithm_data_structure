package leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())",
 * where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		int len = s.length();
		int max = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == '(')
				stack.push(i);
			else {
				if (!stack.empty() && s.charAt(stack.peek()) == '(') {
					stack.pop();
					if (stack.empty())
						max = Math.max(max, i + 1);
					else {
						max = Math.max(max, i - stack.peek());
					}
				} else
					stack.push(i);
			}
		}
		return max;
	}

	public int longestValidParentheses2(String s) {
		LinkedList<Integer> stack = new LinkedList<>();
		int result = 0;
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
				stack.pop();
				result = Math.max(result, i - stack.peek());
			} else {
				stack.push(i);
			}
		}
		return result;
	}

	// DP. Using arrays instead of a stack.
	public int longestValidParentheses3(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int dp[] = new int[s.length()];// dp[i] stores current longest length
		int max = 0, j;

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				j = i - 1 - dp[i - 1];
				if (j >= 0 && s.charAt(j) == '(')
					dp[i] = i - j + 1 + ((j > 0) ? dp[j - 1] : 0);
			}
			if (dp[i] > max)
				max = dp[i];
		}
		return max;
	}
}
