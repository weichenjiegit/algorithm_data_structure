package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *  Given n pairs of parentheses,
 *  write a function to generate all combinations of well-formed parentheses.
 *  
 *  For example, given n = 3, a solution set is:
 *  "((()))", "(()())", "(())()", "()(())", "()()()"
 */
public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		generate(res, 0, 0, n, "");
		return res;
	}

	public void generate(List<String> res, int left, int right, int n, String temp) {
		if (right == n)
			res.add(temp);
		if (left < n)
			generate(res, left + 1, right, n, temp + "(");
		if (right < left)
			generate(res, left, right + 1, n, temp + ")");
	}
}
