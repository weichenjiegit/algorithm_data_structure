package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a digit string,
 * return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * Note:
 * Although the above answer is in lexicographical order,
 * your answer could be in any order you want.
 */
public class LetterCombinationPhoneNumber {

	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		String[] letters = { "0", "1", "abc", "def", "ghi", "jkl", "mno",
				"pqrs", "tuv", "wxyz" };
		get_comb(res, digits, letters, new StringBuilder(""), 0);
		return res;
	}

	public void get_comb(List<String> res, String digits, String[] letters, StringBuilder s, int pos) {
		StringBuilder temp = null;
		if (pos == digits.length()) {
			res.add(s.toString());
			return;
		}
		int num = digits.charAt(pos) - '0';
		for (int i = 0; i < letters[num].length(); i++) {
			temp = new StringBuilder(s);
			temp.append(letters[num].charAt(i));
			get_comb(res, digits, letters, temp, pos + 1);
		}
	}
}
