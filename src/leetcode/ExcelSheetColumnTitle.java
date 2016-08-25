package leetcode;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 
 * For example:
 * 
 * 1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB
 */
public class ExcelSheetColumnTitle {

	public String convertToTitle(int n) {
		String res = "";
		while (n != 0) {
			char ch = (char) ((n - 1) % 26 + 'A');
			n = (n - 1) / 26;
			res = ch + res;
		}
		return res;
	}
}
