import java.util.Scanner;

public class AtmPinLengthValidator {

    // Validates whether the entered PIN is strictly 4 characters long[cite: 4]
    public static void checkPinLength(String pin) {
        if (pin == null) {
            throw new NullPointerException("PIN input reference cannot be null.");
        }

        int pinLength = pin.length();

        if (pinLength != 4) {
            System.out.println("Invalid PIN must be exactly 4 digits."); //[cite: 4]
        } else {
            System.out.println("PIN length OK."); //[cite: 4]
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.nextLine();

            checkPinLength(enteredPin);

        } catch (NullPointerException e) {
            System.err.println("Input Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}