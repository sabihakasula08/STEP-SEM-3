// Abstract class offering overloaded confirmation methods[cite: 19]
abstract class DeliveryNote {
    protected String trackingId;

    public DeliveryNote(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tracking ID cannot be blank.");
        }
        this.trackingId = trackingId.trim();
    }

    public abstract String confirmDelivery();

    // Overloaded method delegating to base confirmDelivery() and appending signature[cite: 19]
    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    public ParcelNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + this.trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    public LetterNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + this.trackingId + " delivered";
    }
}

public class PackageDropOffLogApp {

    // Loops polymorphically over DeliveryNote array[cite: 19]
    public static void logAll(DeliveryNote[] notes) {
        if (notes == null) return;
        for (DeliveryNote note : notes) {
            if (note != null) {
                System.out.println(note.confirmDelivery());
            }
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());              // Parcel TRK-1 delivered[cite: 19]
        System.out.println(p.confirmDelivery("J. Smith"));     // Parcel TRK-1 delivered, signed by J. Smith[cite: 19]

        DeliveryNote ref = p; // Upcasting[cite: 19]
        logAll(new DeliveryNote[]{ref, new LetterNote("TRK-2")});
    }
}