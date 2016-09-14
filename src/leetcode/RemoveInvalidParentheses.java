package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return
 * all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and ).
 * 
 * Examples:
 * 
 * <pre>
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * </pre>
 */
public class RemoveInvalidParentheses {

	// BFS Solution. Each possible removal is counted as a state. Each time removes one parenthesis,
	// adds it to the Queue and checks its validation.
	public List<String> removeInvalidParentheses2(String s) {
		List<String> res = new ArrayList<>();

		// sanity check
		if (s == null)
			return res;

		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();

		// initialize
		queue.add(s);
		visited.add(s);
		int minimumRemovalLength = 0; // Only need valid string in this level
		while (!queue.isEmpty()) {
			s = queue.poll();
			if (s.length() < minimumRemovalLength) {
				return res;
			}
			if (isValid(s)) {
				res.add(s);
				minimumRemovalLength = s.length();
				continue;
			}

			// generate all possible states
			for (int i = 0; i < s.length(); i++) {
				// we only try to remove left or right paren
				if (s.charAt(i) != '(' && s.charAt(i) != ')')
					continue;

				String t = s.substring(0, i) + s.substring(i + 1);
				if (!visited.contains(t)) {
					// for each state, if it's not visited, add it to the queue
					queue.add(t);
					visited.add(t);
				}
			}
		}
		return res;
	}

	boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(')
				count++;
			if (c == ')' && count-- == 0)
				return false;
		}
		return count == 0;
	}
}
