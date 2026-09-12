class PlacementRecord {
    private String studentName;
    private String company;
    private double packageLpa;

    // Constructor to initialize all three placement fields
    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    // Instance method printing formatted placement record
    public void printRecord() {
        System.out.printf("%s -> %s @ %.1f LPA\n", this.studentName, this.company, this.packageLpa);
    }
}

public class PlacementRecordApp {
    public static void main(String[] args) {
        // Array of PlacementRecord objects replacing parallel arrays
        PlacementRecord[] records = new PlacementRecord[3];

        records[0] = new PlacementRecord("Ravi", "TCS", 4.5);
        records[1] = new PlacementRecord("Anitha", "Zoho", 6.2);
        records[2] = new PlacementRecord("Karthik", "Infosys", 4.0);

        // Iterate and print each record
        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}