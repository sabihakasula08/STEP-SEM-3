import java.util.Scanner;

public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty.");
        }

        int vowelCount = 0;
        int consonantCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentChar == ' ') {
                continue;
            }

            char lowerChar = Character.toLowerCase(currentChar);

            if (lowerChar >= 'a' && lowerChar <= 'z') {
                if (lowerChar == 'a' || lowerChar == 'e' || lowerChar == 'i' || lowerChar == 'o' || lowerChar == 'u') {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }

        System.out.printf("Vowels: %d | Consonants: %d\n", vowelCount, consonantCount);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter book title/text: ");
            String userInput = scanner.nextLine();

            countVowelsAndConsonants(userInput);

        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
