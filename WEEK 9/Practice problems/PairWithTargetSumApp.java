import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSumApp {

    // Approach 1: Brute Force Pair Check
    // Time Complexity: O(n^2), Space Complexity: O(1)
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // Approach 2: Hash Set Complement Lookup (Optimal)
    // Time Complexity: O(n), Space Complexity: O(n)
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return false;

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true; // Found matching pair
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Sample 1 Output: " + hasPairWithSum(nums1, target1)); // true (2 + 7 = 9)

        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("Sample 2 Output: " + hasPairWithSum(nums2, target2)); // false
    }
}