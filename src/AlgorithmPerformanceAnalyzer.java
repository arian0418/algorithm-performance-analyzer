import java.util.Arrays;
import java.util.Random;

public class AlgorithmPerformanceAnalyzer {

    private static final int[] INPUT_SIZES = {1_000, 5_000, 10_000, 20_000};
    private static final Random RANDOM = new Random(42);

    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) return i;
        }
        return -1;
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    private static int[] generateData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) data[i] = RANDOM.nextInt(size * 10);
        return data;
    }

    private static double toMilliseconds(long nanoseconds) {
        return nanoseconds / 1_000_000.0;
    }

    private static void benchmarkSorts(int[] original) {
        int[] selectionData = Arrays.copyOf(original, original.length);
        int[] libraryData = Arrays.copyOf(original, original.length);

        long start = System.nanoTime();
        selectionSort(selectionData);
        long selectionTime = System.nanoTime() - start;

        start = System.nanoTime();
        Arrays.sort(libraryData);
        long arraysSortTime = System.nanoTime() - start;

        System.out.printf("%-12d %-20.3f %-20.3f%n",
                original.length, toMilliseconds(selectionTime), toMilliseconds(arraysSortTime));
    }

    private static void benchmarkSearches(int[] sorted) {
        int existingTarget = sorted[sorted.length - 1];

        long start = System.nanoTime();
        int linearIndex = linearSearch(sorted, existingTarget);
        long linearTime = System.nanoTime() - start;

        start = System.nanoTime();
        int binaryIndex = Arrays.binarySearch(sorted, existingTarget);
        long binaryTime = System.nanoTime() - start;

        System.out.printf("%-12d %-20.4f %-20.4f %-10s %-10s%n",
                sorted.length,
                toMilliseconds(linearTime),
                toMilliseconds(binaryTime),
                linearIndex >= 0 ? "found" : "missing",
                binaryIndex >= 0 ? "found" : "missing");
    }

    public static void main(String[] args) {
        System.out.println("ALGORITHM PERFORMANCE ANALYZER");
        System.out.println("==============================================");

        System.out.println("\nSORT BENCHMARK");
        System.out.printf("%-12s %-20s %-20s%n", "Input Size", "Selection Sort (ms)", "Arrays.sort (ms)");

        for (int size : INPUT_SIZES) {
            int[] data = generateData(size);
            benchmarkSorts(data);
        }

        System.out.println("\nSEARCH BENCHMARK");
        System.out.printf("%-12s %-20s %-20s %-10s %-10s%n",
                "Input Size", "Linear Search (ms)", "Binary Search (ms)", "Linear", "Binary");

        for (int size : INPUT_SIZES) {
            int[] data = generateData(size);
            Arrays.sort(data);
            benchmarkSearches(data);
        }

        System.out.println("\nNotes:");
        System.out.println("- Selection sort has O(n^2) time complexity.");
        System.out.println("- Arrays.sort(int[]) uses a highly optimized sorting implementation.");
        System.out.println("- Linear search is O(n), while binary search is O(log n) on sorted data.");
        System.out.println("- Timing results vary by computer, JVM warm-up, and background processes.");
    }
}
