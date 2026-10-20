package com.store;

public class Product {

    // 1. Fields (private = baire theke dhora jabe na)
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    // 2. Constructor (object banar somoy eta chole)
    public Product(String name, String category, double price, int stockQuantity) {
        this.name = name;
        this.category = category;
        setPrice(price);
        setStockQuantity(stockQuantity);
    }

    // 3. Getters (value dekhar jonno)
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    // 4. Setters (value change korar jonno, validation shoho)
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stockQuantity = stockQuantity;
    }

    // 5. toString (print korle sundor dekhabe)
    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f | Stock: %d",
                name, category, price, stockQuantity);
    }
}
