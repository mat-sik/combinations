import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        List<int[]> combs = iterative(3, 5);
        List<List<Integer>> printableCombs = combs.stream().map(arr -> Arrays.stream(arr).boxed().toList()).toList();
        System.out.println(printableCombs);
    }

    /* n = 3 k = 5
     * 1 2 3
     * 1 2 4
     * 1 2 5
     * 1 3 4
     * 1 3 5
     * 1 4 5
     * 2 3 4
     * 2 3 5
     * 2 4 5
     * 3 4 5
     */

    public static List<int[]> iterative(int n, int k) {
        List<int[]> output = new ArrayList<>();
        int[] arr = initArr(n);
        // add first valid combination.
        output.add(arr.clone());
        int i = n - 1;

        while (true) {
            // if you can increment last el, increment it, and we have new valid combination.
            if (arr[i] + 1 <= k) {
                arr[i] += 1;
                output.add(arr.clone());
            } else {
                // go back until you can create valid combination
                do {
                    i--;
                } while (i >= 0 && arr[i] == k - ((n - 1) - i));
                // if you went through all array, no more valid combinations can be generated.
                if (i < 0) {
                    break;
                }
                arr[i] += 1;
                // initialize array to first valid combination given arr[i]
                for (i = i + 1; i < n; i++) {
                    arr[i] = arr[i-1] + 1;
                }
                // start from the back again.
                i = n - 1;
                // add this first new valid combination.
                output.add(arr.clone());
            }
        }

        return output;
    }

    private static int[] initArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
