class LibraryMemberProfile {
    private String membershipId; // write-once[cite: 7]
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash; // write-only[cite: 7]

    // Mandatory public no-arg constructor[cite: 7]
    public LibraryMemberProfile() {
    }

    public String getMembershipId() {
        return this.membershipId;
    }

    // Write-once setter: sets on first call; later calls are silently ignored[cite: 7]
    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // isX convention for boolean JavaBean properties[cite: 7]
    public boolean isPremiumMember() {
        return this.premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // True write-only: one-way transformation, NO getter on the class[cite: 7]
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = "HASH:" + answer.hashCode();
        }
    }
}

public class LibraryMemberJavaBeanApp {
    public static void main(String[] args) {
        LibraryMemberProfile m = new LibraryMemberProfile();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println(m.getMembershipId()); // "LIB-8841"[cite: 7]

        m.setMembershipId("FAKE-0000"); // Silently ignored[cite: 7]
        System.out.println(m.getMembershipId()); // "LIB-8841"[cite: 7]

        System.out.println(m.isPremiumMember()); // true[cite: 7]

        m.setSecurityAnswer("BlueMountain"); // Stored write-only[cite: 7]
    }
}