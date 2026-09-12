import java.util.Scanner;

public class LibraryIsbnValidator {

    // Normalizes input by trimming and uppercasing the initial 3 characters[cite: 4]
    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3); //[cite: 4]
    }

    // Validates 13-character structure and produces formatted display using StringBuilder[cite: 4]
    public static String validateAndFormat(String code) {
        // Validate exact length of 13 characters[cite: 4]
        if (code == null || code.length() != 13) {
            return "Invalid: wrong length"; //[cite: 4]
        }

        // Validate first 3 characters are letters without regex[cite: 4]
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters"; //[cite: 4]
            }
        }

        // Validate remaining 10 characters are digits without regex[cite: 4]
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body"; //[cite: 4]
            }
        }

        // Extract parts: 3-letter publisher, 4-digit year, 6-digit catalog[cite: 4]
        String publisherCode = code.substring(0, 3);
        String yearCode = code.substring(3, 7);
        String catalogNumber = code.substring(7, 13);

        StringBuilder formattedDisplay = new StringBuilder();
        formattedDisplay.append("[").append(publisherCode).append("] ")
                        .append("YEAR: ").append(yearCode)
                        .append(" | CATALOG: ").append(catalogNumber); //[cite: 4]

        return formattedDisplay.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter raw ISBN-style code: ");
            String rawCode = scanner.nextLine();

            String normalizedCode = normalizeCode(rawCode);
            String displayOutput = validateAndFormat(normalizedCode);

            System.out.println(displayOutput);

        } catch (Exception e) {
            System.err.println("Execution Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}