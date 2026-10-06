import java.util.ArrayList;
import java.util.List;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via Credit Card.");
        return true; // Simulate success
    }
}

class PayPalPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via PayPal.");
        return shouldSucceed;
    }
}

class ShoppingProduct {
    private String name;
    private double price;

    public ShoppingProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}

class ShoppingOrder {
    private String customerId;
    private List<ShoppingProduct> products;
    private String status;

    public ShoppingOrder(String customerId) {
        this.customerId = customerId;
        this.products = new ArrayList<>();
        this.status = "Pending";
        System.out.println("Order created for " + customerId + ".");
    }

    public void addProduct(ShoppingProduct product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
    }

    public void pay(PaymentMethod method) {
        if (products.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        double total = 0;
        for (ShoppingProduct p : products) {
            total += p.getPrice();
        }

        boolean success = method.processPayment(total);
        if (success) {
            this.status = "Paid";
            System.out.printf("Payment for Order %s successful. Order status: %s.\n", customerId, this.status);
        } else {
            System.out.printf("Payment for Order %s failed. Order status: %s.\n", customerId, this.status);
        }
    }
}

public class ShoppingPaymentProcessingApp {
    public static void main(String[] args) {
        ShoppingProduct pA = new ShoppingProduct("Product A", 20.0);
        ShoppingProduct pB = new ShoppingProduct("Product B", 15.0);
        ShoppingProduct pC = new ShoppingProduct("Product C", 50.0);

        // Scenario 1: Customer X pays with Credit Card[cite: 69]
        ShoppingOrder orderX = new ShoppingOrder("Customer X");
        orderX.addProduct(pA, 2);
        orderX.addProduct(pB, 1);
        orderX.pay(new CreditCardPayment());

        // Scenario 2: Customer Y empty order[cite: 69]
        ShoppingOrder orderY = new ShoppingOrder("Customer Y");
        orderY.pay(new CreditCardPayment());

        // Scenario 3: Customer Z PayPal failure[cite: 69]
        ShoppingOrder orderZ = new ShoppingOrder("Customer Z");
        orderZ.addProduct(pC, 1);
        orderZ.pay(new PayPalPayment(false));
    }
}