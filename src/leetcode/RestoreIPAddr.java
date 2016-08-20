package leetcode;

/**
 * Given a string containing only digits,
 * restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddr {
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		String cur = "";
		restore(res, cur, s, 4);
		return res;
	}

	public void restore(List<String> res, String cur, String s, int left) {
		if (left == 0) {
			if (s.length() == 0) {
				res.add(cur.substring(0, cur.length() - 1));
				return;
			}
		}
		if (left * 3 < s.length())
			return;
		int num = 0;
		if (s.length() > 0) {
			num = Integer.parseInt(s.substring(0, 1));
			if (num >= 0 && num < 256) {
				String temp = cur + s.substring(0, 1) + ".";
				restore(res, temp, s.substring(1), left - 1);
			}
		}
		if (s.length() > 1) {
			if (s.charAt(0) == '0')
				return;
			num = Integer.parseInt(s.substring(0, 2));
			if (num >= 0 && num < 256) {
				String temp = cur + s.substring(0, 2) + ".";
				restore(res, temp, s.substring(2), left - 1);
			}
		}
		if (s.length() > 2) {
			if (s.charAt(0) == '0')
				return;
			num = Integer.parseInt(s.substring(0, 3));
			if (num >= 0 && num < 256) {
				String temp = cur + s.substring(0, 3) + ".";
				restore(res, temp, s.substring(3), left - 1);
			}
		}
	}

	public List<String> restoreIpAddresses2(String s) {
		List<String> res = new ArrayList<String>();
		int len = s.length();
		for (int i = 1; i < 4 && i < len - 2; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
				for (int k = j + 1; k < j + 4 && k < len; k++) {
					String s1 = s.substring(0, i);
					String s2 = s.substring(i, j);
					String s3 = s.substring(j, k);
					String s4 = s.substring(k, len);
					if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
						res.add(s1 + "." + s2 + "." + s3 + "." + s4);
					}
				}
			}
		}
		return res;
	}

	public boolean isValid(String s) {
		if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
			return false;
		return true;
	}

	public static void main(String[] args) {
		RestoreIPAddr test = new RestoreIPAddr();
		System.out.println(test.restoreIpAddresses2("255255255255"));
	}
}
