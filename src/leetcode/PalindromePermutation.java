package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * 
 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
 * 
 * Hint:
 * 
 * Consider the palindromes of odd vs even length. What difference do you notice?
 * 
 * Count the frequency of each character.
 * 
 * If each character occurs even number of times, then it must be a palindrome. How about character
 * which occurs odd number of times?
 */
public class PalindromePermutation {
	public boolean canPermutePalindrome(String s) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < s.length(); ++i) {
			if (!set.contains(s.charAt(i)))
				set.add(s.charAt(i));
			else
				set.remove(s.charAt(i));
		}
		return set.size() == 0 || set.size() == 1;
	}
}
