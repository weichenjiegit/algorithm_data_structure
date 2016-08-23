package leetcode;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern
 * on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string
 * and make this conversion given a number of rows:
 * 
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
 */
public class ZigzagConversion {
	public String convert(String s, int nRows) {
		StringBuilder sb = new StringBuilder("");
		if (nRows <= 1)
			return s;
		int len = s.length();
		// first row
		for (int i = 0; i < len; i += 2 * nRows - 2) {
			sb.append(s.charAt(i));
		}
		// rows in the middle
		for (int row = 1; row + 1 < nRows; row++) {
			for (int i = row; i < len; i += 2 * nRows - 2) {
				sb.append(s.charAt(i));
				int second_index = i + (nRows - row - 1) * 2;
				if (second_index < len)
					sb.append(s.charAt(second_index));
			}
		}
		// last row
		for (int i = nRows - 1; i < len; i += 2 * nRows - 2) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	// Solution 2
	public String convert2(String s, int nRows) {
		StringBuilder sb[] = new StringBuilder[nRows];
		for (int i = 0; i < nRows; i++)
			sb[i] = new StringBuilder("");
		int index = 0;
		boolean down = true;
		for (int i = 0; i < s.length(); i++) {
			sb[index].append(s.charAt(i));
			if (index == 0)
				down = true;
			else if (index == nRows - 1)
				down = false;
			if (down && index < nRows - 1)
				index++;
			if (!down && index > 0)
				index--;
		}
		StringBuilder res = new StringBuilder("");
		for (int i = 0; i < nRows; i++) {
			res.append(sb[i].toString());
		}
		return res.toString();
	}
}
