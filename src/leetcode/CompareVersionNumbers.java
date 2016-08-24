package leetcode;

/**
 * Compare two version numbers version1 and version2. If version1 > version2 return 1, if version1 <
 * version2 return -1, otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits and the .
 * character. The . character does not represent a decimal point and is used to separate number
 * sequences. For instance, 2.5 is not "two and a half" or "half way to version three", it is the
 * fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 
 * 0.1 < 1.1 < 1.2 < 13.37
 *
 */
public class CompareVersionNumbers {

	public int compareVersion(String version1, String version2) {
		// "." means any character in regex
		// "\." means dot in regex
		String[] levels1 = version1.split("\\.");
		String[] levels2 = version2.split("\\.");

		int length = Math.max(levels1.length, levels2.length);
		for (int i = 0; i < length; i++) {
			Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
			Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
			int compare = v1.compareTo(v2);
			if (compare != 0) {
				return compare;
			}
		}
		return 0;
	}

	// no String.split(String regex)
	public int compareVersion2(String version1, String version2) {
		char[] chars1 = version1.toCharArray();
		char[] chars2 = version2.toCharArray();
		int m = chars1.length;
		int n = chars2.length;
		int i = 0;
		int j = 0;
		int num1 = 0;
		int num2 = 0;
		while (i < m || j < n) {
			while (i < m && chars1[i] != '.') {
				num1 = 10 * num1 + chars1[i] - '0';
				++i;
			}
			while (j < n && chars2[j] != '.') {
				num2 = 10 * num2 + chars2[j] - '0';
				++j;
			}
			if (num1 > num2) {
				return 1;
			}
			if (num1 < num2) {
				return -1;
			}
			num1 = 0;
			num2 = 0;
			++i;
			++j;
		}
		if (i < m)
			return 1;
		if (j < n)
			return -1;
		return 0;
	}
}
