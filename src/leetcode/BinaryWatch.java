package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the
 * bottom represent the minutes (0-59).
 * 
 * Each LED represents a zero or one, with the least significant bit on the right.
 * 
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return
 * all possible times the watch could represent.
 * 
 * Note:
 * 
 * The order of output does not matter.
 * 
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * 
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is
 * not valid, it should be "10:02".
 * 
 */
public class BinaryWatch {
	public List<String> readBinaryWatch(int num) {
		List<String> result = new ArrayList<>();
		help(num, 0, 0, 0, result);
		return result;
	}

	private void help(int num, int pos, int hour, int minute, List<String> result) {
		if (hour > 11 || minute > 59)
			return;
		if (num == 0) {
			result.add("" + hour + ":" + (minute > 9 ? minute : "0" + minute));
			return;
		}
		for (int i = pos; i < 10; i++) { // 10 = 4 + 6, all digits count
			if (i <= 3) { // 1 is in hours
				help(num - 1, i + 1, hour + (1 << (3 - i)), minute, result);
			} else { // 1 is in minutes
				help(num - 1, i + 1, hour, minute + (1 << (9 - i)), result);
			}
		}
	}

	public List<String> readBinaryWatch2(int num) {
		List<String> times = new ArrayList<>();
		for (int h = 0; h < 12; h++)
			for (int m = 0; m < 60; m++)
				if (Integer.bitCount(h * 64 + m) == num)
					times.add(String.format("%d:%02d", h, m));
		return times;
	}
}
