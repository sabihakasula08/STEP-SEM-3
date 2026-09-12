import java.util.Scanner;

public class CsvStudentRecordParser {

    // Splits CSV line and prints formatted record if exactly 3 fields exist[cite: 3]
    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Split CSV line by comma delimiter[cite: 3]
        String[] recordFields = csvLine.split(",");

        // Validate that exactly 3 fields are present[cite: 3]
        if (recordFields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String studentName = recordFields[0].trim();
        String rollNumber = recordFields[1].trim();
        String department = recordFields[2].trim();

        // Check for empty fields within the 3 parts
        if (studentName.isEmpty() || rollNumber.isEmpty() || department.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.printf("Name: %s | Roll No: %s | Dept: %s\n", studentName, rollNumber, department);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter CSV student record (Name, RollNo, Dept): ");
            String rawCsvLine = scanner.nextLine();

            parseStudentRecord(rawCsvLine);

        } catch (Exception e) {
            System.err.println("Execution Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}