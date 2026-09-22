// Common interface with no shared parent lineage[cite: 19]
interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private String trackingId;

    public PackageBox(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tracking ID cannot be blank.");
        }
        this.trackingId = trackingId.trim();
    }

    @Override
    public String printLabel() {
        return "Package label: " + this.trackingId;
    }
}

class Invoice implements Printable {
    private String invoiceNumber;

    public Invoice(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice number cannot be blank.");
        }
        this.invoiceNumber = invoiceNumber.trim();
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + this.invoiceNumber;
    }
}

public class WarehouseLabelPrinterApp {

    // Loops through a mixed array of Printable references polymorphically[cite: 19]
    public static void printAll(Printable[] items) {
        if (items == null) return;
        for (Printable item : items) {
            if (item != null) {
                System.out.println(item.printLabel());
            }
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println(p.printLabel()); // Package label: TRK-88[cite: 19]

        Invoice i = new Invoice("INV-42");
        System.out.println(i.printLabel()); // Invoice label: INV-42[cite: 19]

        printAll(new Printable[]{p, i});
    }
}