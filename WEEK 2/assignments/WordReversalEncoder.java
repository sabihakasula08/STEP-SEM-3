import java.util.Scanner;

public class WordReversalEncoder {

    // Reverses each individual word in a sentence while preserving original word order[cite: 4]
    public static String reverseEachWord(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence cannot be null.");
        }

        // Split sentence into words by space[cite: 4]
        String[] words = sentence.split(" ");
        StringBuilder encodedSentence = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            StringBuilder wordReverser = new StringBuilder();

            // Reverse current word using loop and StringBuilder[cite: 4]
            for (int j = currentWord.length() - 1; j >= 0; j--) {
                wordReverser.append(currentWord.charAt(j));
            }

            encodedSentence.append(wordReverser.toString());

            // Append space between words except after the last word[cite: 4]
            if (i < words.length - 1) {
                encodedSentence.append(" ");
            }
        }

        return encodedSentence.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter sentence to encode: ");
            String inputSentence = scanner.nextLine();

            String encodedResult = reverseEachWord(inputSentence);
            System.out.println(encodedResult);

        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}