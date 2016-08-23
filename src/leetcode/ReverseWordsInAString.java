package leetcode;

/**
 * Given an input string, reverse the string word by word. For example, Given s = "the sky is blue",
 * return "blue is sky the".
 *
 */
public class ReverseWordsInAString {
	// The original version of this problem is to use the algorithm that reverses the string twice.
	// First time reserve all the string, second time reverse each word independently.
	public static String reverseWords(String s) {
		StringBuilder res = new StringBuilder();
		for (int start = s.length() - 1; start >= 0; start--) {
			if (s.charAt(start) == ' ')
				continue;
			int end = start;
			while (start >= 0 && s.charAt(start) != ' ')
				start--;
			res.append(s.substring(start + 1, end + 1)).append(" ");
		}
		return res.toString().trim();
	}
}
