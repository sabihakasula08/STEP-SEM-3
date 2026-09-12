import java.util.Scanner;

public class FileExtensionValidator {

    // Extracts and validates file extension against approved list (pdf, docx, zip)[cite: 3]
    public static String validateFileExtension(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }

        String sanitizedFilename = filename.trim();
        int lastPeriodIndex = sanitizedFilename.lastIndexOf('.');

        // Period must exist and cannot be the first or last character[cite: 3]
        if (lastPeriodIndex == -1 || lastPeriodIndex == sanitizedFilename.length() - 1 || lastPeriodIndex == 0) {
            return "Rejected — invalid file type";
        }

        String extractedExtension = sanitizedFilename.substring(lastPeriodIndex + 1);

        if (extractedExtension.equalsIgnoreCase("pdf")
                || extractedExtension.equalsIgnoreCase("docx")
                || extractedExtension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter filename with extension: ");
            String userFilename = scanner.nextLine();

            String validationResult = validateFileExtension(userFilename);
            System.out.println(validationResult);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
