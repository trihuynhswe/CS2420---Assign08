package assign08;

import java.util.Random;
import java.util.TreeSet;

public class TimngBalancedAndUnbalancedBST {
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
		System.out.println("N\tTreeSet Add (ns)\tBST Add (ns)\tTreeSet Contains (ns)\tBST Contains (ns)");

		// Run for each value of problem size
		for (long N : problemSizes) {
			// Generate random data
			int[] data = generateRandomData((int) N);

			// Timing for TreeSet
			TreeSet<Integer> treeSet = new TreeSet<>();
			long treeSetAddTime = timeAdd(treeSet, data);
			long treeSetContainsTime = timeContains(treeSet, data);

			// Timing for BinarySearchTree
			BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
			long binarySearchTreeAddTime = timeAdd(binarySearchTree, data);
			long binarySearchTreeContainsTime = timeContains(binarySearchTree, data);

			// Print results for this value of N
			System.out.println(N + "\t\t" + treeSetAddTime + "\t\t" + binarySearchTreeAddTime + "\t\t"
					+ treeSetContainsTime + "\t\t" + binarySearchTreeContainsTime);
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

	private static long timeAdd(java.util.Set<Integer> set, int[] data) {
		long totalAddTime = 0;
		for (int t = 0; t < timesToLoop; t++) {
			set.clear();
			long startTime = System.nanoTime();
			for (int item : data) {
				set.add(item);
			}
			long endTime = System.nanoTime();
			totalAddTime += (endTime - startTime);
		}
		return totalAddTime / timesToLoop;
	}

	private static long timeAdd(BinarySearchTree<Integer> bst, int[] data) {
		long totalAddTime = 0;
		for (int t = 0; t < timesToLoop; t++) {
			bst.clear();
			long startTime = System.nanoTime();
			for (int item : data) {
				bst.add(item);
			}
			long endTime = System.nanoTime();
			totalAddTime += (endTime - startTime);
		}
		return totalAddTime / timesToLoop;
	}

	private static long timeContains(java.util.Set<Integer> set, int[] data) {
		long totalContainsTime = 0;
		for (int t = 0; t < timesToLoop; t++) {
			long startTime = System.nanoTime();
			for (int item : data) {
				set.contains(item);
			}
			long endTime = System.nanoTime();
			totalContainsTime += (endTime - startTime);
		}
		return totalContainsTime / timesToLoop;
	}

	private static long timeContains(BinarySearchTree<Integer> bst, int[] data) {
		long totalContainsTime = 0;
		for (int t = 0; t < timesToLoop; t++) {
			long startTime = System.nanoTime();
			for (int item : data) {
				bst.contains(item);
			}
			long endTime = System.nanoTime();
			totalContainsTime += (endTime - startTime);
		}
		return totalContainsTime / timesToLoop;
	}
}
