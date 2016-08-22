package leetcode;

/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class Sqrt {
	public int sqrt(int x) {
		// Newton's method
		if (x == 0)
			return 0;
		double val = x;
		double last;
		do {
			last = val;
			val = (val + x / val) / 2;
		} while (Math.abs(val - last) > 0.000001);
		return (int) val;
	}

	// divide and conquer
	public int sqrt2(int x) {
		long i = 0;
		long j = x / 2 + 1;
		while (i <= j) {
			long mid = (i + j) / 2;
			long sq = mid * mid;
			if (sq == x)
				return (int)mid;
			else if (sq < x)
				i = mid + 1;
			else
				j = mid - 1;
		}
		return (int)j;
	}
	
	public static void main(String[] args){
		Sqrt test = new Sqrt();
		System.out.println(test.sqrt2(25));
	}
}
