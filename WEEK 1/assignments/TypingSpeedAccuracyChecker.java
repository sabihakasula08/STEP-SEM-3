import java.util.Scanner;

public class TypingSpeedAccuracyChecker {

    // Compares original and typed passages character by character[cite: 2]
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            throw new NullPointerException("Text inputs cannot be null.");
        }

        if (original.length() != typed.length()) {
            throw new IllegalArgumentException("Original and typed passages must be of equal length.");
        }

        int totalChars = original.length();
        int matchedCount = 0;
        int firstMismatchPos = -1;
        char originalCharMismatch = '\0';
        char typedCharMismatch = '\0';

        for (int i = 0; i < totalChars; i++) {
            char originalChar = original.charAt(i);
            char typedChar = typed.charAt(i);

            if (originalChar == typedChar) {
                matchedCount++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based index position[cite: 2]
                originalCharMismatch = originalChar;
                typedCharMismatch = typedChar;
            }
        }

        double accuracyPercentage = ((double) matchedCount / totalChars) * 100.0;

        if (firstMismatchPos == -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches\n",
                    matchedCount, totalChars, accuracyPercentage);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d (\'%c\' vs \'%c\')\n",
                    matchedCount, totalChars, accuracyPercentage, firstMismatchPos, originalCharMismatch, typedCharMismatch);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter original passage: ");
            String originalPassage = scanner.nextLine();

            System.out.print("Enter typed passage:    ");
            String typedPassage = scanner.nextLine();

            checkTypingAccuracy(originalPassage, typedPassage);

        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Verification Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}