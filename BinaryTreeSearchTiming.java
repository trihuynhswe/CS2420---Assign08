package assign08;

import java.util.Random;

/**
 * Design and conduct an experiment to illustrate the effect of building an
 * N-item BST by inserting the N items in sorted order versus inserting the N
 * items in a random order. Carefully describe your experiment, so that anyone
 * reading this document could replicate your results.
 * 
 * Plot the results of your experiment. Since the organization of your plot(s)
 * is not specified here, the labels and titles of your plots(s), as well as
 * your interpretation of the plots, are critical.
 * 
 * And here is the suggestion for the experiment: 1. Add N items to a BST in
 * sorted order, then record the time required to invoke the contains method for
 * each item in the BST.
 * 
 * 2. Add the same N items to a new BST in a random order, then record the time
 * required to invoke the contains method for each item in the new BST. (Due to
 * the randomness of this step, you may want to perform it several times and
 * record the average running time required.)
 * 
 * 3. Let one line of the plot be the running times found in #1 for each N in
 * the range [10000, 200000] stepping by 10000. (Change the range, as needed, to
 * complement your machine.) Let the other line of the plot be the running times
 * found in #2 for each N in the same range.
 * 
 */

public class BinaryTreeSearchTiming {

	// Set these numbers for your experiment ///////////////////////////////
	private static final int firstN = 1000; // smallest value of N
	private static final int incrementForN = 1000; // how much N increases by each step
	private static final int numberOfNValues = 15; // number of steps (values of N)
	private static final int timesToLoop = 1000; // number of times to loop for random order
	////////////////////////////////////////////////////////////////////////

	/**
	 * The main method runs the timing experiment, which prints results to the
	 * console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime, endTime;
		Random rand = new Random();

		// You can also manually set values in the array if desired.
		long[] problemSizes = new long[numberOfNValues];
		problemSizes[0] = firstN;
		for (int i = 1; i < numberOfNValues; i++) {
			problemSizes[i] = problemSizes[i - 1] + incrementForN;
		}

		// Print a header
		System.out.println("N\tSorted Order (ns)\tRandom Order (ns)");

		// Run for each value of problem size
		for (long N : problemSizes) {

			///////// ~Sorted Order Timing~ /////////
			BinarySearchTree<Integer> sortedBST = new BinarySearchTree<>();
			for (int i = 0; i < N; i++) {
				sortedBST.add(i);
			}
			int temp;
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				sortedBST.contains(Math.toIntExact(N) / 2);
			}
			endTime = System.nanoTime();
			long sortedOrderTime = (endTime - startTime) / timesToLoop;

			///////// ~Random Order Timing~ /////////
			long totalRandomOrderTime = 0;

			BinarySearchTree<Integer> randomBST = new BinarySearchTree<>();
			int[] randomArray = new int[(int) N];
			for (int i = 0; i < N; i++) {
				randomArray[i] = i;
			}
			shuffleArray(randomArray);

			for (int i = 0; i < N; i++) {
				randomBST.add(randomArray[i]);
			}

			long randomStartTime = System.nanoTime();
			for (int t = 0; t < timesToLoop; t++) {
				randomBST.contains(randomArray[Math.toIntExact(N)/2]);
			}
			long randomEndTime = System.nanoTime();
			long randomOrderTime = (randomEndTime - randomStartTime) / timesToLoop;

			// Print results for this value of N
			System.out.println(N + "\t\t" + sortedOrderTime + "\t\t" + randomOrderTime);
		}
	}

	/**
	 * Helper method to shuffle an array
	 * 
	 * @param array the array to shuffle
	 */
	private static void shuffleArray(int[] array) {
		Random rand = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			// Simple swap
			int a = array[index];
			array[index] = array[i];
			array[i] = a;
		}
	}
}
