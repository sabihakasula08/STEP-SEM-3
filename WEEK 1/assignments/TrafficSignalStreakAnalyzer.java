import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {

    // Scans signal readings to discover the longest consecutive color sequence[cite: 2]
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.trim().isEmpty()) {
            throw new IllegalArgumentException("Signal log sequence cannot be null or empty.");
        }

        // Validate permitted signal identifiers (R, Y, G)[cite: 2]
        String cleanLog = signalLog.trim().toUpperCase();
        for (int i = 0; i < cleanLog.length(); i++) {
            char c = cleanLog.charAt(i);
            if (c != 'R' && c != 'Y' && c != 'G') {
                throw new IllegalArgumentException("Invalid signal code \'" + c + "\'. Allowed codes are R, Y, and G only.");
            }
        }

        char longestColor = cleanLog.charAt(0);
        int maxStreakLength = 1;

        char currentColor = cleanLog.charAt(0);
        int currentStreakLength = 1;

        for (int i = 1; i < cleanLog.length(); i++) {
            if (cleanLog.charAt(i) == currentColor) {
                currentStreakLength++;
            } else {
                if (currentStreakLength > maxStreakLength) {
                    maxStreakLength = currentStreakLength;
                    longestColor = currentColor;
                }
                currentColor = cleanLog.charAt(i);
                currentStreakLength = 1;
            }
        }

        // Final check for streak ending at the last character
        if (currentStreakLength > maxStreakLength) {
            maxStreakLength = currentStreakLength;
            longestColor = currentColor;
        }

        System.out.printf("Longest Streak: \'%c\' repeated %d times\n", longestColor, maxStreakLength);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter traffic signal log (e.g., RRGGGYRR): ");
            String logInput = scanner.nextLine();

            findLongestStreak(logInput);

        } catch (IllegalArgumentException e) {
            System.err.println("Signal Log Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}