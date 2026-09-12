import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    // Categorizes words into Short, Medium, or Long brackets based on character count[cite: 2]
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            throw new IllegalArgumentException("Review content cannot be empty.");
        }

        // Split by whitespace escape sequence (\s+)[cite: 2]
        String[] words = review.trim().split("\\s+");

        int shortCount = 0;   // 1 - 4 letters[cite: 2]
        int mediumCount = 0;  // 5 - 8 letters[cite: 2]
        int longCount = 0;    // 9+ letters[cite: 2]

        for (String word : words) {
            // Strip out non-alphabetic punctuation marks to measure pure letter length
            String sanitizedWord = word.replaceAll("[^a-zA-Z]", "");
            int wordLength = sanitizedWord.length();

            if (wordLength == 0) {
                continue;
            }

            if (wordLength >= 1 && wordLength <= 4) {
                shortCount++;
            } else if (wordLength >= 5 && wordLength <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d\n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter movie review:");
            String reviewText = scanner.nextLine();

            classifyWordLengths(reviewText);

        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}