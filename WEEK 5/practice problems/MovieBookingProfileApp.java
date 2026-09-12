class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp; // write-only[cite: 8]

    // Mandatory public no-arg constructor[cite: 8]
    public MovieBookingProfile() {
    }

    // Convenience constructor chaining via this()[cite: 8]
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() { // isX convention for boolean properties[cite: 8]
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only: stores value, no matching getter provided[cite: 8]
    public void setOtp(String otp) {
        this.otp = otp;
    }
}

public class MovieBookingProfileApp {
    public static void main(String[] args) {
        System.out.println(new MovieBookingProfile("Rahul Dev").getName()); // Rahul Dev[cite: 8]

        MovieBookingProfile profile = new MovieBookingProfile("Rahul Dev");
        profile.setConfirmed(true);
        System.out.println(profile.isConfirmed()); // true[cite: 8]

        profile.setOtp("4471"); // Sets securely without external readout[cite: 8]
    }
}