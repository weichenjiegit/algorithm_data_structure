package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string, S, and a list of words, L, that are all of the same length. Find all
 * starting indices of substring(s) in S that is a concatenation of each word in L exactly once and
 * without any intervening characters.
 * 
 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 */
public class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		int wordsNum = words.length;
		if (wordsNum <= 0)
			return res;
		Map<String, Integer> countByWord = new HashMap<>();
		Map<String, Integer> countByWordInCurString = new HashMap<>();
		for (String word : words) {
			if (countByWord.containsKey(word))
				countByWord.put(word, countByWord.get(word) + 1);
			else
				countByWord.put(word, 1);
		}
		int len = words[0].length();
		for (int i = 0; i <= s.length() - wordsNum * len; i++) {
			// i is the start point of substring in s
			// the end part that is less than words_num * len does not matter
			countByWordInCurString.clear();
			for (int j = 0; j < wordsNum; j++) {
				String curWord = s.substring(i + j * len, i + j * len + len);// get a word
				if (!countByWord.containsKey(curWord)) // not match, next iteration
					break;
				if (countByWordInCurString.containsKey(curWord)) // update current count
					countByWordInCurString.put(curWord, countByWordInCurString.get(curWord) + 1);
				else
					countByWordInCurString.put(curWord, 1);
				if (countByWordInCurString.get(curWord) > countByWord.get(curWord))
					break;
				if (j == wordsNum - 1) // all words get mapped
					res.add(i);
			}
		}
		return res;
	}
}
