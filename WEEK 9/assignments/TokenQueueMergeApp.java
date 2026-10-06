import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenQueueMergeApp {

    // Merges two sorted lists using the Two-Pointer technique without re-sorting[cite: 45, 46]
    public static List<Integer> mergeTokens(List<Integer> counterA, List<Integer> counterB) {
        List<Integer> merged = new ArrayList<>();
        int pA = 0;
        int pB = 0;
        int lenA = (counterA != null) ? counterA.size() : 0;
        int lenB = (counterB != null) ? counterB.size() : 0;

        while (pA < lenA && pB < lenB) {
            if (counterA.get(pA) <= counterB.get(pB)) {
                merged.add(counterA.get(pA));
                pA++;
            } else {
                merged.add(counterB.get(pB));
                pB++;
            }
        }

        // Append remaining tokens from counterA
        while (pA < lenA) {
            merged.add(counterA.get(pA));
            pA++;
        }

        // Append remaining tokens from counterB
        while (pB < lenB) {
            merged.add(counterB.get(pB));
            pB++;
        }

        return merged;
    }

    public static void main(String[] args) {
        // Sample 1
        List<Integer> counterA1 = Arrays.asList(3, 8, 15, 20);
        List<Integer> counterB1 = Arrays.asList(5, 8, 12);
        System.out.println("Sample 1 Output: " + mergeTokens(counterA1, counterB1)); // [3, 5, 8, 8, 12, 15, 20]

        // Sample 2
        List<Integer> counterA2 = Arrays.asList();
        List<Integer> counterB2 = Arrays.asList(4, 9);
        System.out.println("Sample 2 Output: " + mergeTokens(counterA2, counterB2)); // [4, 9]
    }
}