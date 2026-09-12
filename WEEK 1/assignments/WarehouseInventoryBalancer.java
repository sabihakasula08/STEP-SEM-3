import java.util.InputMismatchException;
import java.util.Scanner;

public class WarehouseInventoryBalancer {

    // Analyzes balance and tracks highest quantity item across both warehouse sections[cite: 2]
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null) {
            throw new NullPointerException("Inventory sections cannot be null.");
        }

        if (sectionA.length != sectionB.length || sectionA.length == 0) {
            throw new IllegalArgumentException("Both sections must have an identical, non-zero number of categories.");
        }

        int sectionATotal = 0;
        int sectionBTotal = 0;

        int highestQuantity = Integer.MIN_VALUE;
        String highestSection = "";
        int highestItemIndex = -1;

        for (int i = 0; i < sectionA.length; i++) {
            // Section A checks
            sectionATotal += sectionA[i];
            if (sectionA[i] > highestQuantity) {
                highestQuantity = sectionA[i];
                highestSection = "Section A";
                highestItemIndex = i + 1; // 1-based index[cite: 2]
            }

            // Section B checks
            sectionBTotal += sectionB[i];
            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = "Section B";
                highestItemIndex = i + 1;
            }
        }

        String balanceStatus = (sectionATotal == sectionBTotal) ? "Balanced" : "Not Balanced";

        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)\n",
                sectionATotal, sectionBTotal, balanceStatus, highestQuantity, highestSection, highestItemIndex);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter number of item categories per section: ");
            int categoryCount = scanner.nextInt();

            if (categoryCount <= 0) {
                System.out.println("Category count must be greater than zero.");
                return;
            }

            int[] sectionA = new int[categoryCount];
            int[] sectionB = new int[categoryCount];

            System.out.println("Enter Section A quantities:");
            for (int i = 0; i < categoryCount; i++) {
                sectionA[i] = scanner.nextInt();
            }

            System.out.println("Enter Section B quantities:");
            for (int i = 0; i < categoryCount; i++) {
                sectionB[i] = scanner.nextInt();
            }

            analyzeInventory(sectionA, sectionB);

        } catch (InputMismatchException e) {
            System.err.println("Format Error: Only whole numbers are allowed for quantities.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Inventory Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}