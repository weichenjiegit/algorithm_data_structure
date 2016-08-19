package leetcode;

public class Pow {
	public double pow(double x, int n) {
		if (n == 0)
			return 1;
		if (x == 0)
			return 0;
		boolean sign = false;
		if (n < 0) {
			sign = true;
			n = -n;
		}
		double temp = x;
		double result = 1;
		while (n > 0) {
			if ((n & 1) > 0)// even or odd
				result *= temp;
			temp *= temp;
			n >>= 1;
		}
		return sign ? 1.0 / result : result;
	}

	public double pow2(double x, int n) {
		if (n == 0)
			return 1;
		if (n < 0) {
			n = -n;
			x = 1 / x;
		}
		return (n % 2 == 0) ? pow(x * x, n / 2) : x * pow(x * x, n / 2);
	}

	public static void main(String[] args) {
		Pow test = new Pow();
		test.pow(10, 7);
	}
}
