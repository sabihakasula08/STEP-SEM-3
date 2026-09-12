class Item {
    private String itemName;
    private int stock;

    // Resolves field/parameter clash using this.field = param[cite: 6]
    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    // Resolves clash using this.stock[cite: 6]
    public void restock(int stock) {
        this.stock = this.stock + stock;
    }

    public void printItemStock() {
        System.out.printf("%s | Final Stock: %d\n", this.itemName, this.stock);
    }
}

public class CanteenRestockApp {
    public static void main(String[] args) {
        Item[] shelfItems = {
            new Item("Samosa", 15),
            new Item("Tea Powder", 40),
            new Item("Bread", 8),
            new Item("Biscuit Packs", 25)
        };

        int restockAmount = 20;

        for (Item item : shelfItems) {
            item.restock(restockAmount);
            item.printItemStock();
        }
    }
}
