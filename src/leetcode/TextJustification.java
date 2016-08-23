package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L
 * characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each
 * line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces
 * on a line do not divide evenly between words, the empty slots on the left will be assigned more
 * spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is inserted between
 * words.
 * 
 * <pre>
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * 
 * Return the formatted lines as:
 * 
 * [
 *  "This    is    an",
 *  "example  of text",
 *  "justification.  "
 * ]
 * 
 * Corner case
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 * </pre>
 * 
 * Note: Each word is guaranteed not to exceed L in length.
 */
public class TextJustification {
	public List<String> fullJustify(String[] words, int L) {
		List<String> res = new ArrayList<>();
		if (words.length == 0)
			return res;
		int index = 0;
		StringBuilder sb = new StringBuilder();
		while (index < words.length) {
			sb = new StringBuilder();
			int volume = 0; // number of chars this line
			int cur_len = 0;// number of chars plus one space for each word
			int counter = 0;
			int initial = index;
			while (index < words.length && words[index].length() + cur_len <= L) {
				cur_len += (words[index].length() + 1);// at least one space
				volume += words[index].length();
				counter++;
				index++;
			}
			if (index == words.length) {// if last line, all words go left
				while (initial < index) {
					sb.append(words[initial++]);
					if (sb.length() < L)
						sb.append(" ");// one space per word, if length permits
						               // for the last word
				}
				while (sb.length() < L)
					sb.append(" ");// add spaces till end of last line
				res.add(sb.toString());
				break;
			}
			int spaces = L - volume;
			if (counter != 1) {
				int spaces_per_word = spaces / (counter - 1);
				int extra_spaces = spaces % (counter - 1);
				while (initial < index) {// no "=", since index points to the
				                         // next word now
					sb.append(words[initial++]);
					if (--counter == 0)// the last word does not need space
						break;
					if (extra_spaces > 0) {
						for (int i = 0; i < spaces_per_word; i++)
							sb.append(" ");
						sb.append(" ");// extra one apace for each word
						extra_spaces--;
					} else {
						for (int i = 0; i < spaces_per_word; i++)
							sb.append(" ");
					}
				}
			} else {// if only one word this line, all spaces go right
				sb.append(words[initial]);
				for (int i = 0; i < spaces; i++)
					sb.append(" ");
			}
			res.add(sb.toString());
		}
		return res;
	}
}
