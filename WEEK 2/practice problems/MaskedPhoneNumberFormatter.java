import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    // Validates 10-digit number and formats with StringBuilder mask[cite: 3]
    public static String maskPhoneNumber(String phone) {
        if (phone == null) {
            return "Invalid phone number";
        }

        String cleanedPhone = phone.trim();

        // Must be exactly 10 characters long[cite: 3]
        if (cleanedPhone.length() != 10) {
            return "Invalid phone number";
        }

        // Verify each character is numeric via ASCII code boundary (0-9: 48-57)[cite: 3]
        for (int i = 0; i < cleanedPhone.length(); i++) {
            char ch = cleanedPhone.charAt(i);
            if (ch < '0' || ch > '9') {
                return "Invalid phone number";
            }
        }

        // Construct masked number: XXXXXX followed by last 4 digits with a hyphen[cite: 3]
        String lastFourDigits = cleanedPhone.substring(6);
        StringBuilder maskedBuilder = new StringBuilder("XXXXXX");
        maskedBuilder.append("-").append(lastFourDigits);

        return maskedBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter 10-digit phone number: ");
            String rawPhoneNumber = scanner.nextLine();

            String formattedResult = maskPhoneNumber(rawPhoneNumber);
            System.out.println(formattedResult);

        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
