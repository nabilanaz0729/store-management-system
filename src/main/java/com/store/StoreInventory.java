package com.store;

import java.util.ArrayList;
import java.util.List;

public class StoreInventory {

    // Shob product ekhane thakbe (private, baire theke dhora jabe na)
    private final List<Product> products = new ArrayList<>();

    // 1. Product add kora
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    // 2. Nam diye product khuje ber kora (case-insensitive)
    //    Pele Product return kore, na pele null
    public Product findProduct(String productName) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(productName)) {
                return p;
            }
        }
        return null;
    }

    // 3. Nam diye product remove kora
    //    Remove hole true, na pele false
    public boolean removeProduct(String productName) {
        Product found = findProduct(productName);
        if (found == null) {
            return false;
        }
        products.remove(found);
        return true;
    }

    // 4. Category diye search kora
    public List<Product> searchByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    // 5. Shob product dekhano
    public void listInventory() {
        System.out.println("===== STORE INVENTORY =====");
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            int number = 1;
            for (Product p : products) {
                System.out.println(number + ". " + p);
                number++;
            }
        }
        System.out.println("===========================");
    }

    // 6. Shob product er copy dey (file e save korar somoy lagbe)
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    // ===== ORDER PROCESSING =====

    public OrderSummary createOrder(String productName, int quantity) {

        // Step 1: input check
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        // Step 2: product ache kina
        Product product = findProduct(productName);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + productName);
        }

        // Step 3: stock jothesto kina
        if (product.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName()
                    + ". Available: " + product.getStockQuantity()
                    + ", requested: " + quantity);
        }

        // Step 4: hishab
        double unitPrice = product.getPrice();
        double subtotal = roundMoney(unitPrice * quantity);
        double discountRate = getDiscountRate(subtotal);
        double finalTotal = roundMoney(subtotal - (subtotal * discountRate));

        // Step 5: shob check pass korle tokhon stock komao
        product.setStockQuantity(product.getStockQuantity() - quantity);

        // Step 6: result return
        return new OrderSummary(product.getName(), quantity, unitPrice, discountRate, finalTotal);
    }

    // Subtotal dekhe discount rate thik kore
    private double getDiscountRate(double subtotal) {
        if (subtotal >= 500) {
            return 0.15;
        } else if (subtotal >= 200) {
            return 0.10;
        } else if (subtotal >= 100) {
            return 0.05;
        } else {
            return 0.0;
        }
    }

    // Taka 2 doshomik ghor e round kore
    private double roundMoney(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

}