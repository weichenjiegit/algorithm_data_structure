package leetcode;
/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {
	public String countAndSay(int n) {
		String cur = "1";
		StringBuilder sb = new StringBuilder("1");
		for(int i = 1; i < n; i++){
			sb = new StringBuilder("");
			int len = cur.length();
			int count = 1;
			for(int j = 0; j < len; j++){
				while(j + 1 < len && cur.charAt(j) == cur.charAt(j + 1)){
					j++;
					count++;
				}
				sb.append(count);
				sb.append(cur.charAt(j));
				count = 1;
			}
			cur = sb.toString();
		}
		return sb.toString();
	}
}
