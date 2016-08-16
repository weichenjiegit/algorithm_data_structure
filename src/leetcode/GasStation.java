package leetcode;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to
 * its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise
 * return -1.
 * 
 * Note: The solution is guaranteed to be unique.
 */
public class GasStation {

	// Solution 1 Brute Force. Fail.
	public int canCompleteCircuit(int[] gas, int[] cost) {
		for (int i = 0; i < gas.length; i++) {
			int curGas = gas[i];
			int curPos = i;
			int nextPos = (i + 1) % gas.length;
			while (true) {
				if (curGas < cost[curPos])
					break;
				curGas = curGas - cost[curPos] + gas[nextPos];
				curPos = nextPos;
				nextPos = (++nextPos) % gas.length;
				if (curPos == i)
					return i;
			}
		}
		return -1;
	}

	// Solution 2. Sum records valid index. Total means if there is a valid result
	public int canCompleteCircuit2(int[] gas, int[] cost) {
		int sum = 0, total = 0, len = gas.length, index = -1;
		for (int i = 0; i < len; i++) {
			sum += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if (sum < 0) {
				index = i;
				sum = 0;
			}
		}
		return total >= 0 ? index + 1 : -1;
	}

	public static void main(String[] args) {
		GasStation test = new GasStation();
		test.canCompleteCircuit(new int[] { 2, 4 }, new int[] { 3, 4 });
	}
}
