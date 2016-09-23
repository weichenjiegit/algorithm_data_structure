package leetcode;

import java.util.Arrays;

/**
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large
 * positive integer given in the form of an array.
 * 
 * <pre>
 * Example1:
 * 
 * a = 2
 * b = [3]
 * 
 * Result: 8
 * 
 * Example2:
 * 
 * a = 2
 * b = [1,0]
 * 
 * Result: 1024
 * </pre>
 */
public class SuperPow {
	/**
	 * Basic math knowlege:
	 * 
	 * (ab)%k=(a%k)(b%k)%k;
	 * 
	 * (abc)%k=(a%k)(b%k)(c%k)%k
	 * 
	 * 23^1335%base
	 * 
	 * =(23^1330%base)(23^5%base)%base
	 * 
	 * =((23^133%base)^10)%base(23^5%base)%base
	 * 
	 * =function(function(23,133),10)*function(23,5)%base
	 */
	private int base = 1337;

	public int superPow(int a, int[] b) {
		if (b.length == 0)
			return 1;
		int lastDigit = b[b.length - 1];
		int[] copyArr = Arrays.copyOf(b, b.length - 1);

		return powMod(superPow(a, copyArr), 10) * powMod(a, lastDigit) % base;
	}

	private int powMod(int a, int n) {
		a %= base;
		int res = 1;
		for (int i = 0; i < n; i++) {
			res = (res * a) % base;
		}
		return res;
	}

	// Avoid copy of arrays.
	public int superPow2(int a, int[] b) {
		return superPow2(a, b, b.length, 1337);
	}

	private int superPow2(int a, int[] b, int length, int k) {
		if (length == 1) {
			return powMod(a, b[0], k);
		}
		return powMod(superPow2(a, b, length - 1, k), 10, k) * powMod(a, b[length - 1], k) % k;
	}

	// x^y mod k
	private int powMod(int x, int y, int k) {
		x %= k;
		int pow = 1;
		for (int i = 0; i < y; i++) {
			pow = (pow * x) % k;
		}
		return pow;
	}
}
