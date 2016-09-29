package leetcode;

/**
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 * 
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * 
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes
 * with most significant 2 bits being 10.
 * 
 * <pre>
 * This is how the UTF-8 encoding would work:
 * 
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * </pre>
 * 
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 * 
 * Note: The input is an array of integers. Only the least significant 8 bits of each integer is
 * used to store the data. This means each integer represents only 1 byte of data.
 */
public class UTF8Validation {

	public boolean validUtf8(int[] data) {
		int n = data.length;
		for (int i = 0; i < n; i++) {
			if ((data[i] & 0x80) != 0) {
				if ((data[i] & 0xe0) == 0xc0) {
					if (i + 1 >= n || (data[i + 1] & 0xc0) != 0x80) {
						return false;
					}
					i += 1;
				} else if ((data[i] & 0xf0) == 0xe0) {
					if (i + 2 >= n || (data[i + 1] & 0xc0) != 0x80 || (data[i + 2] & 0xc0) != 0x80) {
						return false;
					}
					i += 2;
				} else if ((data[i] & 0xf8) == 0xf0) {
					if (i + 3 >= n || (data[i + 1] & 0xc0) != 0x80 || (data[i + 2] & 0xc0) != 0x80
					        || (data[i + 3] & 0xc0) != 0x80) {
						return false;
					}
					i += 3;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public boolean validUtf82(int[] data) {
		if (data == null || data.length == 0)
			return false;
		boolean isValid = true;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > 255)
				return false; // 1 after 8th digit, 100000000
			int numberOfBytes = 0;
			if ((data[i] & 128) == 0) { // 0xxxxxxx, 1 byte, 128(10000000)
				numberOfBytes = 1;
			} else if ((data[i] & 224) == 192) { // 110xxxxx, 2 bytes, 224(11100000), 192(11000000)
				numberOfBytes = 2;
			} else if ((data[i] & 240) == 224) { // 1110xxxx, 3 bytes, 240(11110000), 224(11100000)
				numberOfBytes = 3;
			} else if ((data[i] & 248) == 240) { // 11110xxx, 4 bytes, 248(11111000), 240(11110000)
				numberOfBytes = 4;
			} else {
				return false;
			}
			// check that the next n bytes start with 10xxxxxx
			for (int j = 1; j < numberOfBytes; j++) {
				if (i + j >= data.length)
					return false;
				if ((data[i + j] & 192) != 128)
					return false; // 192(11000000), 128(10000000)
			}
			i = i + numberOfBytes - 1;
		}
		return isValid;
	}
}
