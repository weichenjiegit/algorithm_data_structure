package leetcode;

/**
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: Carefully consider all possible input cases.
 * If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes:
 * It is intended for this problem to be specified vaguely
 * (ie, no given input specs).
 * You are responsible to gather all the input requirements up front.
 * 
 * Requirements for atoi:
 * The function first discards as many whitespace characters as necessary
 * until the first non-whitespace character is found.
 * Then, starting from this character,
 * takes an optional initial plus or minus sign
 * followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 * 
 * The string can contain additional characters after those
 * that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * 
 * If the first sequence of non-whitespace characters in str
 * is not a valid integral number,
 * or if no such sequence exists
 * because either str is empty or it contains only whitespace characters,
 * no conversion is performed.
 * 
 * If no valid conversion could be performed,
 * a zero value is returned.
 * If the correct value is out of the range of representable values,
 * INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 */
public class StringToInteger {
	// Note: do not forget the "sign" before comparing sum to MIN or MAX
	public int atoi(String str) {
		if (str == null || str.length() == 0)
			return 0;
		int index = 0;
		int sign = 1;
		while (str.charAt(index) == ' ')
			index++;
		if (str.charAt(index) == '+')
			index++;
		else if (str.charAt(index) == '-') {
			sign = -1;
			index++;
		}
		long sum = 0;
		for (int i = index; i < str.length(); i++) {
			if (str.charAt(i) <= '9' && str.charAt(i) >= '0')
				sum = sum * 10 + str.charAt(i) - '0';
			else
				break;
		}
		sum = sign * sum;
		int res = 0;
		if (sum > Integer.MAX_VALUE)
			res = Integer.MAX_VALUE;
		else if (sum < Integer.MIN_VALUE)
			res = Integer.MIN_VALUE;
		else
			res = (int) sum;
		return res;
	}
	
	public static void main(String[] args){
		StringToInteger test = new StringToInteger();
		System.out.println(test.atoi("1"));
	}
}
