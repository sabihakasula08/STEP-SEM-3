import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StopWordFilteredReport {

    // Filters stop words and prints remaining word frequencies sorted in descending order[cite: 4]
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            throw new IllegalArgumentException("Feedback paragraph cannot be null or empty.");
        }

        // Fixed array of common filler words to ignore[cite: 4]
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        // Normalize: convert to lowercase and strip punctuation marks with replace()[cite: 4]
        String cleanedFeedback = feedback.toLowerCase()
                                         .replace(".", "")
                                         .replace(",", "")
                                         .replace("!", "")
                                         .replace("?", "");

        // Split text into words using whitespace pattern[cite: 4]
        String[] words = cleanedFeedback.trim().split("\\s+");

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            // Check if current word exists in stop-word list[cite: 4]
            boolean isStopWord = false;
            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            // Count frequency for non-stop words[cite: 4]
            if (!isStopWord) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Sort unique words by count descending[cite: 4]
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort((firstEntry, secondEntry) -> secondEntry.getValue().compareTo(firstEntry.getValue()));

        // Print frequency breakdown[cite: 4]
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue()); //[cite: 4]
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter feedback paragraph:");
            String feedbackInput = scanner.nextLine();

            printFilteredWordFrequency(feedbackInput);

        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}