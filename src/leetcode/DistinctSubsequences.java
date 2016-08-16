package leetcode;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining
 * characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 */
public class DistinctSubsequences {
	/**
	 *  S  0123....j
	 * T  ----------
	 *   |1111111111|
	 * 0 |0         |
	 * 1 |0         |
	 * 2 |0         |
	 * . |0         |
	 * . |0         |
	 * i |0         |
	 * 
	 * S: [acdabefbc] and T: [ab]
	 * first we check with a:
	 *           [*  *     ]
	 *       S = [acdabefbc]
	 * mem[1] = [0111222222]
	 * 
	 * then we check with ab:
	 * 
	 *           [    *  * ]
	 *       S = [acdabefbc]
	 * mem[1] = [0111222222]
	 * mem[2] = [0000022244]
	 * 
	 * And the result is 4, as the distinct subsequences are:
	 * S = [a   b    ]
	 * S = [a      b ]
	 * S = [   ab    ]
	 * S = [   a   b ]
	 * 
	 * if the new character in S matches the new character T
	 * then the number we had without the two new characters
	 * (mem[i][j]) T[0, i - 1] matches S[0, j - 1]
	 * plus the number we had with the new character in T without the character in S
	 * (mem[i + 1][j]) T[0, i] matches S[0, j - 1]
	 */
	public int numDistinct1(String s, String t) {
		// array creation
		int[][] mem = new int[t.length() + 1][s.length() + 1];

		// filling the first row: with 1s
		for (int j = 0; j <= s.length(); j++) {
			mem[0][j] = 1;
		}

		// the first column is 0 by default in every other rows but the first, which we need.
		for (int i = 0; i < t.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (t.charAt(i) == s.charAt(j)) {
					mem[i + 1][j + 1] = mem[i][j] + mem[i + 1][j];
				} else {
					mem[i + 1][j + 1] = mem[i + 1][j];
				}
			}
		}

		return mem[t.length()][s.length()];
	}

	// counter[i][j] the number of distinct subsequence in S[0, i - 1] and T[0, j - 1]
	// counter[0][0] means S = "" and T = ""
	public int numDistinct2(String S, String T) {
		int ns = S.length();
		int nt = T.length();
		if (ns < nt)
			return 0;
		int[][] counter = new int[ns + 1][nt + 1];
		for (int i = 0; i <= ns; i++)
			counter[i][0] = 1;
		for (int i = 1; i <= ns; i++) {
			for (int j = 1; j <= nt; j++) {
				if (i < j)
					counter[i][j] = 0;
				else {
					// if the new character in S doesn't equal to the new character T
					// then the same number of distinct subsequences without the new character.
					// if the new character in S equal to the new character T
					// then the number we had without the new character (counter[i - 1][j - 1])
					// plus the number we had with the new character in S (counter[i - 1][j]).
					if (S.charAt(i - 1) == T.charAt(j - 1))
						counter[i][j] = counter[i - 1][j - 1] + counter[i - 1][j];
					else
						counter[i][j] = counter[i - 1][j];
				}
			}
		}
		return counter[ns][nt];
	}

	// Reduce the useless traversal of half the matrix
	public int numDistinct3(String S, String T) {
		int ns = S.length();
		int nt = T.length();
		if (ns < nt)
			return 0;
		int[][] counter = new int[ns + 1][nt + 1];
		for (int i = 0; i <= ns; i++)
			counter[i][0] = 1;
		for (int j = 1; j <= nt; j++) {
			for (int i = j; i <= ns; i++) {
				if (S.charAt(i - 1) == T.charAt(j - 1))
					counter[i][j] = counter[i - 1][j - 1] + counter[i - 1][j];
				else
					counter[i][j] = counter[i - 1][j];
			}
		}
		return counter[ns][nt];
	}

	public static void main(String[] args) {
		DistinctSubsequences test = new DistinctSubsequences();
		test.numDistinct1("baba", "a");
	}
}
