package comprehensive;

/**
 * A class used to test the performance and growth-rate behavior of
 * the RandomPhraseGenerator class
 * 
 * @author Jordan Hensley, Romney Doria, jHensley, doria CS 2420-fall 2015
 *         comprehensive  Last Updated: 12/08/2015
 *
 */
public class Timing {

	public static void main(String[] args) {
		// Do 10000 lookups and use the average running time.
		int timesToLoop = 10;

		// For each problem size n . . .
		for (int n = 10000; n <= 200000; n += 10000) {

			// Setup of n size for testing
			long startTime, midpointTime, stopTime;

			String[] arguments = {"RPG_Timing_Tests/RPG_Time1.g", "" + n};
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			// Run the methods for testing
			for (int i = 0; i < timesToLoop; i++) {
				RandomPhraseGenerator.main(arguments);
			}

			midpointTime = System.nanoTime();

			// Time it takes to run loop
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + " " + averageTime);
		}
	}
}
