package com.store;

public class OrderSummary {

    private final String productName;
    private final int quantity;
    private final double unitPrice;
    private final double discountRate;   // jemon 0.10 mane 10%
    private final double finalTotal;

    public OrderSummary(String productName, int quantity, double unitPrice,
                        double discountRate, double finalTotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountRate = discountRate;
        this.finalTotal = finalTotal;
    }

    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getDiscountRate() { return discountRate; }
    public double getFinalTotal() { return finalTotal; }

    @Override
    public String toString() {
        return String.format(
                "Order: %s | Qty: %d | Unit Price: $%.2f | Discount: %.0f%% | Final Total: $%.2f",
                productName, quantity, unitPrice, discountRate * 100, finalTotal);
    }
}