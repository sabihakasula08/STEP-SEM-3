import java.util.Scanner;

public class BankTransactionReferenceValidator {

    // Normalizes input by trimming and upper-casing the 3-letter bank code[cite: 3]
    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        // Uppercase first 3 characters and leave the rest untouched[cite: 3]
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    // Validates 14-char structure and formats into readable display line[cite: 3]
    public static String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length (must be exactly 14 characters)";
        }

        // Validate first 3 characters are letters without regex[cite: 3]
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Validate remaining 11 characters are digits without regex[cite: 3]
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain 11 digits";
            }
        }

        // Extract components: 3 letters + 6 digits (ddMMyy) + 5 digits (sequence)[cite: 3]
        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequenceNumber = reference.substring(9, 14);

        // Build formatted line using StringBuilder[cite: 3]
        StringBuilder formattedDisplay = new StringBuilder();
        formattedDisplay.append("[").append(bankCode).append("] ")
                        .append("DATE: ").append(day).append("/").append(month).append("/").append(year)
                        .append(" | SEQ: ").append(sequenceNumber);

        return formattedDisplay.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter transaction reference code: ");
            String rawInput = scanner.nextLine();

            String normalized = normalizeReference(rawInput);
            String outputResult = validateAndFormat(normalized);

            System.out.println(outputResult);

        } catch (Exception e) {
            System.err.println("Processing Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}