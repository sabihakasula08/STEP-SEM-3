public class TicketPriceSlotApp {

    // Binary search returning existing slot or insertion position (lower bound)
    public static int findSlot(int[] prices, int newPrice) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow

            if (prices[mid] == newPrice) {
                return mid; // Exact match found
            } else if (prices[mid] < newPrice) {
                low = mid + 1; // Move right
            } else {
                high = mid - 1; // Move left
            }
        }

        // When not found, low points to the exact insertion index
        return low;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        // Sample 1
        System.out.println("findSlot(prices, 150) -> Expected: 1 | Actual: " + findSlot(prices, 150)); // 1

        // Sample 2[cite: 48]
        System.out.println("findSlot(prices, 210) -> Expected: 3 | Actual: " + findSlot(prices, 210)); // 3[cite: 48]

        // Sample 3[cite: 48]
        System.out.println("findSlot(prices, 300) -> Expected: 4 | Actual: " + findSlot(prices, 300)); // 4[cite: 48]
    }
}