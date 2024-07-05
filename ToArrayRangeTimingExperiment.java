package assign08;

import java.util.Random;

public class ToArrayRangeTimingExperiment {
	private static final int firstN = 10000; // smallest value of N
	private static final int incrementForN = 10000; // how much N increases by each step
	private static final int numberOfNValues = 20; // number of steps (values of N)
	private static final int timesToLoop = 10; // number of times to loop for averaging

	public static void main(String[] args) {
		long[] problemSizes = new long[numberOfNValues];
		problemSizes[0] = firstN;
		for (int i = 1; i < numberOfNValues; i++) {
			problemSizes[i] = problemSizes[i - 1] + incrementForN;
		}

		// Print headers
		System.out.println("N\ttoArrayRange Time (ns)");

		// Run for each value of problem size
		for (long N : problemSizes) {
			// Generate random data
			int[] data = generateRandomData((int) N);

			// Timing for toArrayRange
			BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
			for (int item : data) {
				binarySearchTree.add(item);
			}

			long toArrayRangeTime = timeToArrayRange(binarySearchTree, data);

			// Print results for this value of N
			System.out.println(N + "\t" + toArrayRangeTime);
		}
	}

	private static int[] generateRandomData(int N) {
		Random rand = new Random();
		int[] data = new int[N];
		for (int i = 0; i < N; i++) {
			data[i] = rand.nextInt();
		}
		return data;
	}

	private static long timeToArrayRange(BinarySearchTree<Integer> bst, int[] data) {
		long totalToArrayRangeTime = 0;
		for (int t = 0; t < timesToLoop; t++) {
			long startTime = System.nanoTime();
			bst.toArrayRange(data[0], data[data.length - 1]);
			long endTime = System.nanoTime();
			totalToArrayRangeTime += (endTime - startTime);
		}
		return totalToArrayRangeTime / timesToLoop;
	}
}
