package leetcode;

/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInteger {
	public int romanToInt(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (i + 1 < s.length() && getVal(cur) < getVal(s.charAt(i + 1))) {
				res -= getVal(cur);
			} else
				res += getVal(cur);
		}
		return res;
	}

	public int getVal(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}
}
