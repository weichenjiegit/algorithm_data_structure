package leetcode;

/**
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * 
 * Note: It is intended for the problem statement to be ambiguous.
 * You should gather all requirements up front before implementing one.
 */
public class ValidNumber {
	// Solution 1
	public boolean isNumber(String s) {
		if (s == null || s.length() == 0)
			return false;
		int index = 0;
		index = skipWhiteSpaces(s, index);
		index = skipSigns(s, index);
		int n1 = skipDigits(s, index);
		index += n1;
		if (index < s.length() && s.charAt(index) == '.')
			index++;
		int n2 = skipDigits(s, index);
		index += n2;
		if (n1 == 0 && n2 == 0)
			return false;
		if (index < s.length()
				&& (s.charAt(index) == 'e' || s.charAt(index) == 'E')) {
			index++;
			index = skipSigns(s, index);
			int n3 = skipDigits(s, index);
			if (n3 == 0)
				return false;
			index += n3;
		}
		index = skipWhiteSpaces(s, index);
		return index == s.length();
	}
	public int skipWhiteSpaces(String s, int index){
		while(index < s.length() && s.charAt(index) == ' ')
			index++;
		return index;
	}
	public int skipSigns(String s, int index){
		if(index < s.length() && (s.charAt(index) == '+' || s.charAt(index) == '-'))
			return index + 1;
		return index;
	}
	public int skipDigits(String s, int index){
		int counter = index;
		while(counter < s.length() && s.charAt(counter) >= '0' && s.charAt(counter) <= '9')
			counter++;
		return counter - index;
	}

	// Solution 2
	public int index;

	public boolean isNumber2(String s) {
		index = 0;
		if (s.length() == 0)
			return false;
		// remove spaces
		while (index < s.length() && s.charAt(index) == ' ')
			index++;

		// check the part before e is number or not
		if (!isNum(s, false))
			return false;
		// check the part after e is number or not
		if (index < s.length() && s.charAt(index) == 'e') {
			index++;
			if (!isNum(s, true))
				return false;
		}

		while (index < s.length() && s.charAt(index) == ' ')
			index++;
		if (index == s.length())
			return true;
		else
			return false;
	}

	public boolean isNum(String s, boolean flag) {
		boolean res = false;
		if (index < s.length()
				&& (s.charAt(index) == '-' || s.charAt(index) == '+'))
			index++;
		boolean dot = false;
		while (index < s.length()) {
			if (s.charAt(index) <= '9' && s.charAt(index) >= '0') {
				res = true;
				index++;
			} else if (s.charAt(index) == '.') {
				if (flag) // the part after e has no dot
					return false;
				if (dot) // more than one dot
					return false;
				dot = true;
				index++;
			} else if (s.charAt(index) == 'e' || s.charAt(index) == ' ')
				return res;
			else
				return false;
		}
		return res;
	}
	
	public static void main(String[] args){
		ValidNumber test = new ValidNumber();
		test.isNumber("0e");
	}
}
