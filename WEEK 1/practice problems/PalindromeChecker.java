import java.util.Arrays;
import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return Arrays.equals(original, reversed);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to check: ");
        String input = scanner.nextLine();

        boolean iter = isPalindromeIterative(input);
        boolean recur = isPalindromeRecursive(input);
        boolean arrRev = isPalindromeArrayReversal(input);

        System.out.printf("Iterative: %s | Recursive: %s | Array Reversal: %s\n",
                iter ? "Palindrome" : "Not Palindrome",
                recur ? "Palindrome" : "Not Palindrome",
                arrRev ? "Palindrome" : "Not Palindrome");

        scanner.close();
    }
}