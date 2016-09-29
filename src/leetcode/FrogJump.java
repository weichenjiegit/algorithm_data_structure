package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may
 * not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 * 
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is
 * able to cross the river by landing on the last stone. Initially, the frog is on the first stone
 * and assume the first jump must be 1 unit.
 * 
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
 * Note that the frog can only jump in the forward direction.
 * 
 * Note:
 * 
 * The number of stones is ≥ 2 and is < 1,100.
 * 
 * Each stone's position will be a non-negative integer < 231.
 * 
 * The first stone's position is always 0.
 */
public class FrogJump {
	/**
	 * Use map to represent a mapping from the stone (not index) to the steps that can be taken from
	 * this stone.
	 * 
	 * so this will be
	 * 
	 * [0,1,3,5,6,8,12,17]
	 * 
	 * {17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4,
	 * 5]}
	 * 
	 * Notice that no need to calculate the last stone.
	 * 
	 * On each step, we look if any other stone can be reached from it, if so, we update that
	 * stone's steps by adding step, step + 1, step - 1. If we can reach the final stone, we return
	 * true. No need to calculate to the last stone.
	 */
	public boolean canCross(int[] stones) {
		if (stones.length == 0) {
			return true;
		}

		Map<Integer, Set<Integer>> map = new HashMap<>(stones.length);
		map.put(0, new HashSet<Integer>());
		map.get(0).add(1);
		for (int i = 1; i < stones.length; i++) {
			map.put(stones[i], new HashSet<Integer>());
		}

		for (int i = 0; i < stones.length - 1; i++) {
			int stone = stones[i];
			for (int step : map.get(stone)) {
				int reach = step + stone;
				if (reach == stones[stones.length - 1]) {
					return true;
				}
				Set<Integer> set = map.get(reach);
				if (set != null) {
					set.add(step);
					if (step - 1 > 0) // if 0 step, then stays at current stone.
						set.add(step - 1);
					set.add(step + 1);
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		new FrogJump().canCross(new int[] { 0, 1, 3, 4, 5, 7, 9, 10, 12 });
	}
}
