package leetcode;

/**
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from
 * 1 to 3999.
 */
public class IntegerToRoman {
	public String intToRoman(int num) {
		String[] symbol = { "M", "D", "C", "L", "X", "V", "I" };
		int[] base = { 1000, 500, 100, 50, 10, 5, 1 };
		int index = 0;
		int counter = 0;
		StringBuilder res = new StringBuilder("");
		while (index < symbol.length) {
			if (num - base[index] >= 0) {
				num -= base[index];
				counter++;
			} else {
				if (counter == 4) {
					res.append(symbol[index]);
					res.append(symbol[index - 1]);
					counter = 0;
				} else if (counter >= 5 && counter < 9) {
					res.append(symbol[index - 1]);
					counter -= 5;
				} else if (counter == 9) {
					res.append(symbol[index]);
					res.append(symbol[index - 2]);
					counter = 0;
				}
				while (counter > 0) {
					res.append(symbol[index]);
					counter--;
				}
				index += 2;
			}
		}
		return res.toString();
	}

	public static String intToRoman2(int num) {
		String M[] = { "", "M", "MM", "MMM" };
		String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}
}
