import java.util.Scanner;

public class ProductInventoryParser {

    // Splits CSV line and outputs a formatted inventory record if 3 fields are present[cite: 4]
    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record"); //[cite: 4]
            return;
        }

        // Split CSV row by comma[cite: 4]
        String[] fields = csvLine.split(",");

        // Validate that exactly 3 fields are present[cite: 4]
        if (fields.length != 3) {
            System.out.println("Invalid Record"); //[cite: 4]
            return;
        }

        String productName = fields[0].trim();
        String skuCode = fields[1].trim();
        String quantity = fields[2].trim();

        if (productName.isEmpty() || skuCode.isEmpty() || quantity.isEmpty()) {
            System.out.println("Invalid Record"); //[cite: 4]
            return;
        }

        System.out.printf("Product: %s | SKU: %s | Qty: %s\n", productName, skuCode, quantity); //[cite: 4]
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter inventory CSV line: ");
            String rawCsvInput = scanner.nextLine();

            parseInventoryRecord(rawCsvInput);

        } catch (Exception e) {
            System.err.println("Parsing Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}