package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Write a program to find the nth super ugly number.
 * 
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list
 * primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the
 * first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 * 
 * Note:
 * 
 * (1) 1 is a super ugly number for any given primes.
 * 
 * (2) The given numbers in primes are in ascending order.
 * 
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */
public class SuperUglyNumber {
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] ugly = new int[n];
		int[] indexForPrime = new int[primes.length];

		ugly[0] = 1;
		for (int i = 1; i < n; i++) {
			// find next
			ugly[i] = Integer.MAX_VALUE;
			for (int j = 0; j < primes.length; j++)
				ugly[i] = Math.min(ugly[i], primes[j] * ugly[indexForPrime[j]]);

			// skip duplicate
			for (int j = 0; j < primes.length; j++) {
				while (primes[j] * ugly[indexForPrime[j]] <= ugly[i])
					indexForPrime[j]++;
			}
		}

		return ugly[n - 1];
	}

	// Saves multiplication operation and one for loop. Need an additional array.
	public int nthSuperUglyNumber2(int n, int[] primes) {
		int[] ugly = new int[n];
		int[] indexForPrime = new int[primes.length];
		int[] val = new int[primes.length];
		Arrays.fill(val, 1);

		int next = 1;
		for (int i = 0; i < n; i++) {
			ugly[i] = next;

			next = Integer.MAX_VALUE;
			for (int j = 0; j < primes.length; j++) {
				// skip duplicate and avoid extra multiplication
				if (val[j] == ugly[i]) {
					val[j] = ugly[indexForPrime[j]] * primes[j];
					indexForPrime[j]++;
				}
				// find next ugly number
				next = Math.min(next, val[j]);
			}
		}

		return ugly[n - 1];
	}

	// Saves even more. No additional array needed.
	public int nthSuperUglyNumber3(int n, int[] primes) {
		int[] res = new int[n];
		res[0] = 1;
		int[] cur = new int[primes.length];

		for (int i = 1; i < n; i++) {
			res[i] = Integer.MAX_VALUE;
			for (int j = 0; j < primes.length; j++) {
				if (primes[j] * res[cur[j]] == res[i - 1]) {
					cur[j]++;
				}
				res[i] = Math.min(res[i], primes[j] * res[cur[j]]);
			}
		}
		return res[n - 1];
	}

	public int nthSuperUglyNumberHeap4(int n, int[] primes) {
		int[] ugly = new int[n];

		PriorityQueue<Num> pq = new PriorityQueue<>();
		for (int i = 0; i < primes.length; i++)
			pq.add(new Num(primes[i], 1, primes[i]));
		ugly[0] = 1;

		for (int i = 1; i < n; i++) {
			ugly[i] = pq.peek().val;
			while (pq.peek().val == ugly[i]) {
				Num next = pq.poll();
				pq.add(new Num(next.p * ugly[next.idx], next.idx + 1, next.p));
			}
		}

		return ugly[n - 1];
	}

	private class Num implements Comparable<Num> {
		int val;
		int idx;
		int p;

		public Num(int val, int idx, int p) {
			this.val = val;
			this.idx = idx;
			this.p = p;
		}

		@Override
		public int compareTo(Num that) {
			return this.val - that.val;
		}
	}
}
