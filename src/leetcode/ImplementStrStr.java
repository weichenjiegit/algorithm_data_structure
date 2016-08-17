package leetcode;

/**
 * Implement strStr(). Returns a pointer to the first occurrence of needle in haystack, or null if
 * needle is not part of haystack.
 */
public class ImplementStrStr {
	// Solution 1. Brute force solution.
	public int strStr(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}

	// KMP solution.
	public String strStr2(String haystack, String needle) {
		if (haystack == null)
			return null;
		if (needle == null)
			return haystack;
		int len1 = haystack.length();
		int len2 = needle.length();
		if (len2 == 0)
			return haystack;
		if (len2 > len1)
			return null;
		int[] prefix = new int[len2];
		int k = 0;
		prefix[0] = 0;
		// Compute prefix function
		for (int q = 1; q < len2; q++) {
			while (k > 0 && needle.charAt(k) != needle.charAt(q))
				k = prefix[k - 1];// pay attention here
			if (needle.charAt(k) == needle.charAt(q))
				k++;
			prefix[q] = k;
		}
		int q = 0;
		for (int i = 0; i < len1; i++) {
			while (q > 0 && needle.charAt(q) != haystack.charAt(i))
				q = prefix[q - 1];// pay attention here
			if (needle.charAt(q) == haystack.charAt(i))
				q++;
			if (q == len2)
				return haystack.substring(i - len2 + 1);
		}
		return null;
	}

	public static void main(String[] args) {
		String haystack = "cdeababababacbdef";
		String needle = "ababacb";
		ImplementStrStr test = new ImplementStrStr();
		System.out.println(test.strStr2(haystack, needle));
	}
}
