package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDnaSequences {

	public List<String> findRepeatedDnaSequences(String s) {
		Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
		for (int i = 0; i + 9 < s.length(); i++) {
			String ten = s.substring(i, i + 10);
			if (!seen.add(ten))
				repeated.add(ten);
		}
		return new ArrayList<String>(repeated);
	}

	public List<String> findRepeatedDnaSequences2(String s) {
		List<String> res = new ArrayList<String>();
		if (s.length() < 10)
			return res;
		Set<Integer> once = new HashSet<Integer>();
		Set<Integer> twice = new HashSet<Integer>();
		// Hashing function. We have only 4 letters which we can represent by 2 bits.
		int[] map = new int[26];
		map['A' - 'A'] = 0; // A = 00
		map['C' - 'A'] = 1; // B = 01
		map['G' - 'A'] = 2; // C = 10
		map['T' - 'A'] = 3; // D = 11
		int enc = 0; // enc acts like a rolling window
		for (int i = 0; i < 9; ++i) {
			enc <<= 2;
			enc |= map[s.charAt(i) - 'A'];
		}
		for (int j = 9; j < s.length(); ++j) {
			enc <<= 2;
			enc &= 0xfffff; // only leaves with 20 bits = 10 characters
			enc |= map[s.charAt(j) - 'A'];
			if (!once.add(enc) && twice.add(enc))
				res.add(s.substring(j - 9, j + 1));
		}
		return res;
	}
}
