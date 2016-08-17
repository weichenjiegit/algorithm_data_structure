package leetcode;

/**
 * Given a string s consists of upper/lower-case alphabets
 * and empty space characters ' ',
 * return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence
 * consists of non-space characters only.
 * 
 * For example,
 * Given s = "Hello World",
 * return 5.
 */
public class LengthLastWord {
	public int lengthOfLastWord(String s) {
		int len = s.length();
		boolean last_word = false;
		int last_word_pos = len - 1;
		for (int i = len - 1; i >= 0; i--) {
			if (!last_word && s.charAt(i) != ' ') {
				last_word = true;
				last_word_pos = i;
			}
			if (last_word && s.charAt(i) == ' ')
				return last_word_pos - i;
			if (last_word && i == 0)// s.charAt(i) != ' '
				return last_word_pos + 1;
			if (!last_word && i == 0)
				return 0;
		}
		return 0;
	}

	public int lengthOfLastWord2(String s) {
		if (s.length() == 0)
			return 0;
		int i = s.length() - 1;
		int count = 0;
		while (i >= 0 && s.charAt(i) == ' ')
			--i;
		if (i == -1)
			return 0;
		while (i >= 0 && s.charAt(i) != ' ') {
			++count;
			--i;
		}
		return count;
	}
}
