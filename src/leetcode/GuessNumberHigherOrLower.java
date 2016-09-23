package leetcode;

/**
 * We are playing the Guess Game. The game is as follows:
 * 
 * I pick a number from 1 to n. You have to guess which number I picked.
 * 
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * 
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * 
 * -1 : My number is lower
 * 
 * 1 : My number is higher
 * 
 * 0 : Congrats! You got it!
 */
public class GuessNumberHigherOrLower {
	public int guessNumber(int n) {
		int low = 1, high = n;
		int g = -1;
		while (low <= high) {
			g = low + (high - low) / 2;
			if (guess(g) == 0) {
				break;
			} else if (guess(g) == 1) {
				low = g + 1;
			} else {
				high = g - 1;
			}
		}
		return g;
	}

	// dummy method
	private int guess(int guess) {
		return 0;
	}
}
