class ParkingTicket {
    private String vehicleNo;
    private double ratePerMinute;

    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    // Final method locked against overriding[cite: 6]
    public final double calculateFine(int overstayMinutes) {
        return overstayMinutes * this.ratePerMinute;
    }

    // Final method printing receipt[cite: 6]
    public final void printReceipt(int overstayMinutes) {
        double fine = calculateFine(overstayMinutes);
        System.out.printf("%s - Fine: Rs %.1f\n", this.vehicleNo, fine);
    }

    public String getVehicleNo() {
        return this.vehicleNo;
    }
}

public class ParkingFineCalculatorApp {
    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"};
        double[] ratePerMinute = {2.0, 2.0, 3.0, 2.0};
        int[] overstayMinutes = {15, 0, -5, 8};

        for (int i = 0; i < vehicleNos.length; i++) {
            ParkingTicket ticket = new ParkingTicket(vehicleNos[i], ratePerMinute[i]);
            if (overstayMinutes[i] > 0) {
                ticket.printReceipt(overstayMinutes[i]);
            } else {
                System.out.printf("%s - No fine, within allotted time\n", ticket.getVehicleNo());
            }
        }
    }
}