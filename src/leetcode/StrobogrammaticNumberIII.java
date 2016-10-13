package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at
 * upside down).
 * 
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num
 * <= high.
 * 
 * For example, Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three
 * strobogrammatic numbers.
 * 
 * Note: Because the range might be a large number, the low and high numbers are represented as
 * string.
 */
public class StrobogrammaticNumberIII {
	private char[][] pairs = { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' }, { '9', '6' } };
	private int count = 0;

	public int strobogrammaticInRange(String low, String high) {
		for (int len = low.length(); len <= high.length(); len++) {
			dfs(low, high, new char[len], 0, len - 1);
		}
		return count;
	}

	private void dfs(String low, String high, char[] chars, int left, int right) {
		if (left > right) {
			String s = new String(chars);
			if ((s.length() == low.length() && s.compareTo(low) < 0)
			        || (s.length() == high.length() && s.compareTo(high) > 0))
				return;
			count++;
			return;
		}

		for (char[] p : pairs) {
			chars[left] = p[0];
			chars[right] = p[1];
			if (chars.length != 1 && chars[0] == '0')
				continue;
			if (left < right || left == right && p[0] == p[1])
				dfs(low, high, chars, left + 1, right - 1);
		}
	}

	public int strobogrammaticInRange2(String low, String high) {
		int count = 0;
		List<String> rst = new ArrayList<>();
		for (int n = low.length(); n <= high.length(); n++) {
			rst.addAll(helper(n, n));
		}
		for (String num : rst) {

			if ((num.length() == low.length() && num.compareTo(low) < 0)
			        || (num.length() == high.length() && num.compareTo(high) > 0))
				continue;
			count++;
		}
		return count;
	}

	private List<String> helper(int cur, int max) {
		if (cur == 0)
			return new ArrayList<String>(Arrays.asList(""));
		if (cur == 1)
			return new ArrayList<String>(Arrays.asList("1", "8", "0"));

		List<String> rst = new ArrayList<String>();
		List<String> center = helper(cur - 2, max);

		for (int i = 0; i < center.size(); i++) {
			String tmp = center.get(i);
			if (cur != max)
				rst.add("0" + tmp + "0");
			rst.add("1" + tmp + "1");
			rst.add("6" + tmp + "9");
			rst.add("8" + tmp + "8");
			rst.add("9" + tmp + "6");
		}
		return rst;
	}
}
