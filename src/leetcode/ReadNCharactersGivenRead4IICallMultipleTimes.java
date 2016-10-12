package leetcode;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it returns 3 if there is
 * only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters
 * from the file.
 * 
 * Note: The read function may be called multiple times.
 */
public class ReadNCharactersGivenRead4IICallMultipleTimes {

	public class Solution extends Reader4 {
		private int buffIndex = 0; // last index
		private int buffCount = 0; // count for characters from previous read
		private char[] buff = new char[4];

		/**
		 * @param buf
		 *            Destination buffer
		 * @param n
		 *            Maximum number of characters to read
		 * @return The number of characters read
		 */
		public int read(char[] buf, int n) {
			int index = 0;
			while (index < n) {
				if (buffIndex == 0) {
					buffCount = read4(buff);
				}
				if (buffCount == 0)
					break;
				while (index < n && buffIndex < buffCount) {
					buf[index++] = buff[buffIndex++];
				}
				if (buffIndex >= buffCount)
					buffIndex = 0;
			}
			return index;
		}
	}

	/**
	 * Dummy parent class.
	 */
	public class Reader4 {
		protected int read4(char[] buf) {
			return 0;
		}
	}
}
